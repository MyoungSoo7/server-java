package kr.hhplus.be.server.coupons.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.repository.CustomerRepository;
import kr.hhplus.be.server.coupons.dto.CouponType;
import kr.hhplus.be.server.coupons.entity.Coupons;
import kr.hhplus.be.server.coupons.entity.UserCoupons;
import kr.hhplus.be.server.payment.port.outbound.CouponRepository;
import kr.hhplus.be.server.payment.port.outbound.UserCouponsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//엔터티 객체의 비즈니스 로직을 조합하거나 처리
public class CouponService {

	private static final String COUPON_NOT_FOUND = "존재하지 않는 쿠폰입니다.";
	private static final String CUSTOMER_NOT_FOUND = "사용자가 존재하지 않습니다.";

	private final CouponRepository couponRepository;
	private final CustomerRepository customerRepository;
	private final UserCouponsRepository userCouponRepository;

	public void createCoupon() {
		Coupons coupons = Coupons.builder()
			.couponName("쿠폰이름")
			.couponType(String.valueOf(CouponType.DISCOUNT))
			.couponDiscountRate(5)
			.quota(10) // 발급 한도 추가
			.issuedCount(0) // 초기 발급 수 설정
			.build();
		couponRepository.save(coupons);

	}

	//선착순 쿠폰 발급 API (사용자는 선착순으로 할인 쿠폰을 발급받을 수 있습니다.)
	public boolean issueCouponFirstComeFirstServe(Long customerId, Long couponId) {
		Coupons coupons = getCouponById(couponId);

		// 쿠폰이 이미 발급 한도를 초과하지 않았는지 확인
		if (isQuotaExceeded(coupons)) {
			return false; // 발급 불가
		}
		// 쿠폰 발급 수 증가 및 쿠폰 매핑 저장
		incrementCouponIssuedCount(coupons);
		saveUserCouponMapping(getCustomerById(customerId), coupons);
		return true; // 발급 성공
	}

	// 보유 쿠폰 목록 조회 API 를 작성합니다.
	public Optional<Coupons> getUserCoupons(Long customerId) {
		return couponRepository.findById(customerId);
	}

	public int calculateDiscount(Coupons coupon, int totalPrice) {
		switch (CouponType.valueOf(coupon.getCouponType())) {
			case DISCOUNT:
				return coupon.getCouponPrice();
			case DISCOUNT_RATE:
				return (totalPrice * coupon.getCouponDiscountRate()) / 100;
			default:
				throw new IllegalArgumentException("알 수 없는 쿠폰 유형입니다.");
		}

	}

	@Transactional
	public void useCoupon(Long couponId, Long userId) {
		UserCoupons coupon = userCouponRepository.findById(couponId)
			.orElseThrow(() -> new IllegalArgumentException("유효하지 않은 쿠폰입니다."));

		if (coupon.isUsed()) {
			throw new IllegalArgumentException("이미 사용된 쿠폰입니다.");
		}

		Customers customer = getCustomerById(userId);
		markCouponAsUsed(coupon, customer);

	}

	private Customers getCustomerById(Long customerId) {
		return customerRepository.findById(customerId)
			.orElseThrow(() -> new IllegalArgumentException(CUSTOMER_NOT_FOUND));
	}

	private void markCouponAsUsed(UserCoupons userCoupon, Customers customer) {
		userCoupon.setUsed(true);
		userCoupon.setCustomers(customer); // 누구에게 사용되었는지 저장(옵션)
		userCouponRepository.save(userCoupon);
	}

	private Coupons getCouponById(Long couponId) {
		return couponRepository.findById(couponId)
			.orElseThrow(() -> new IllegalArgumentException(COUPON_NOT_FOUND));
	}

	private boolean isQuotaExceeded(Coupons coupon) {
		return coupon.getIssuedCount() >= coupon.getQuota();
	}

	private void incrementCouponIssuedCount(Coupons coupon) {
		coupon.setIssuedCount(coupon.getIssuedCount() + 1);
		couponRepository.save(coupon);
	}

	private void saveUserCouponMapping(Customers customer, Coupons coupon) {
		UserCoupons userCoupon = UserCoupons.builder()
			.customers(customer)
			.coupon(coupon)
			.isUsed(false)
			.build();
		userCouponRepository.save(userCoupon);
	}

}

package kr.hhplus.be.server.payment.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.repository.CustomerRepository;
import kr.hhplus.be.server.payment.dto.CouponType;
import kr.hhplus.be.server.payment.entity.Coupons;
import kr.hhplus.be.server.payment.entity.UserCoupons;
import kr.hhplus.be.server.payment.repository.CouponRepository;
import kr.hhplus.be.server.payment.repository.UserCouponsRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {

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
		// 쿠폰 유효성 검사
		Coupons coupons = couponRepository.findById(couponId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));

		// 쿠폰이 이미 발급 한도를 초과하지 않았는지 확인
		if (coupons.getIssuedCount() >= coupons.getQuota()) {
			return false; // 발급 불가
		}

		// 발급 처리( 발급수 업데이트)
		coupons.setIssuedCount(coupons.getIssuedCount() + 1);
		couponRepository.save(coupons);

		Customers customer = customerRepository.findById(customerId)
			.orElseThrow(() -> new IllegalArgumentException("Customer not found"));

		// 사용자가 해당 쿠폰을 발급받은 기록 생성
		UserCoupons userCoupon = UserCoupons.builder()
			.customers(customer)
			.coupon(coupons)
			.isUsed(false)
			.build();
		userCouponRepository.save(userCoupon); // 사용자-쿠폰 매핑 저장

		return true; // 발급 성공
	}


    // 보유 쿠폰 목록 조회 API 를 작성합니다.
	public Optional<Coupons> getUserCoupons(Long customerId) {
		return couponRepository.findById(customerId);
	}
}

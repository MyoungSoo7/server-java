package kr.hhplus.be.server.payment.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.payment.dto.CouponType;
import kr.hhplus.be.server.payment.entity.Coupons;
import kr.hhplus.be.server.payment.repository.CouponRepository;
import lombok.RequiredArgsConstructor;

/*선착순 쿠폰 발급 API 및 보유 쿠폰 목록 조회 API 를 작성합니다.
사용자는 선착순으로 할인 쿠폰을 발급받을 수 있습니다.
주문 시에 유효한 할인 쿠폰을 함께 제출하면, 전체 주문금액에 대해 할인 혜택을 부여받을 수 있습니다.*/
@Service
@RequiredArgsConstructor
public class CouponService {

	private final CouponRepository couponRepository;

	public void createCoupon() {
		Coupons coupons = Coupons.builder()
				.couponName("쿠폰이름")
				.couponType(String.valueOf(CouponType.DISCOUNT))
				.couponDiscountRate(5)
				.build();
		couponRepository.save(coupons);

	}

	public boolean issueCouponFirstComeFirstServe(Long userId, Long couponId) {
		// 쿠폰 유효성 검사
		Coupons coupons = couponRepository.findById(couponId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));

		// 쿠폰이 이미 발급 한도를 초과하지 않았는지 확인
		if (coupons.getIssuedCount() >= coupons.getQuota()) {
			return false; // 발급 불가
		}

		// 발급 처리
		coupons.setIssuedCount(coupons.getIssuedCount() + 1);
		couponRepository.save(coupons);

		// 사용자에게 쿠폰 발급
		Coupons userCoupons = Coupons.builder()
			.id(couponId)
			.build();

		// UserCoupon 엔티티 사용자동작 Repository가 있다고 가정합니다.
		//couponRepository.save(userCoupons);

		return true; // 발급 성공
	}

	public Optional<Coupons> getUserCoupons(Long userId) {
		return couponRepository.findById(userId);
	}
}

package kr.hhplus.be.server.payment.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.payment.entity.Coupons;
import kr.hhplus.be.server.payment.service.CouponService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("coupons")
@RequiredArgsConstructor
public class CouponController {


	private final CouponService couponService;

	// 쿠폰 생성 API
	@PostMapping("/v1/create")
	public ResponseEntity<String> createCoupon() {
		couponService.createCoupon();
		return ResponseEntity.ok("쿠폰이 생성되었습니다.");
	}

	// 선착순 쿠폰 발급 API
	@PostMapping("/issue/{userId}/{couponId}")
	public ResponseEntity<String> issueCoupon(@PathVariable Long userId, @PathVariable Long couponId) {
		boolean result = couponService.issueCouponFirstComeFirstServe(userId, couponId);
		if (result) {
			return ResponseEntity.ok("쿠폰 발급에 성공했습니다.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("쿠폰 발급에 실패했습니다.");
		}
	}

	// 보유 쿠폰 조회 API
	@GetMapping("/user/{userId}")
	public ResponseEntity<Coupons> getUserCoupons(@PathVariable Long userId) {
		Optional<Coupons> userCoupons = couponService.getUserCoupons(userId);
		return userCoupons.map(ResponseEntity::ok)
			.orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
}

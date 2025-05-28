package kr.hhplus.be.server.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.payment.entity.Coupons;
import kr.hhplus.be.server.payment.entity.UserCoupons;

@Repository
public interface UserCouponsRepository extends JpaRepository<UserCoupons, Long> {
	UserCoupons findByUserIdAndCouponId(Long userId, Long couponId);
	UserCoupons findByCouponId(Long couponId);
	UserCoupons findByUserId(Long userId);
}

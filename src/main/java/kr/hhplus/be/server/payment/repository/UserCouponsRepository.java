package kr.hhplus.be.server.payment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.payment.entity.Coupons;
import kr.hhplus.be.server.payment.entity.UserCoupons;

@Repository
public interface UserCouponsRepository extends JpaRepository<UserCoupons, Long> {
	List<UserCoupons> findAllByUserId(Long userId);
	UserCoupons findByUserIdAndCouponId(Long userId, Long couponId);
	UserCoupons findByCouponId(Long couponId);
	UserCoupons findByUserId(Long userId);
}

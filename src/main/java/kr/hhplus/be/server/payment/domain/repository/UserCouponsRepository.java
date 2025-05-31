package kr.hhplus.be.server.payment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.payment.domain.entity.UserCoupons;

@Repository
public interface UserCouponsRepository extends JpaRepository<UserCoupons, Long> {

}

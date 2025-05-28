package kr.hhplus.be.server.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.payment.entity.Coupons;

@Repository
public interface CouponRepository extends JpaRepository<Coupons, Long > {

}

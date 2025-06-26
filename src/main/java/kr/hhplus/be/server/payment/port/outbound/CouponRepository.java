package kr.hhplus.be.server.payment.port.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.coupons.entity.Coupons;

@Repository
//리포지토리 및 외부 API 연결 인터페이스를 정의
public interface CouponRepository extends JpaRepository<Coupons, Long > {

}

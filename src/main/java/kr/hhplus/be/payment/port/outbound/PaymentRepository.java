 package kr.hhplus.be.payment.port.outbound;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.payment.domain.dto.PaymentDto;
import kr.hhplus.be.payment.domain.entity.Payments;

 //리포지토리 및 외부 API 연결 인터페이스를 정의
@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

	PaymentDto findByUserId(Long userId);

}


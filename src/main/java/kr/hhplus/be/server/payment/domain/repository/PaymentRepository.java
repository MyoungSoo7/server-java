 package kr.hhplus.be.server.payment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.payment.application.dto.PaymentDto;
import kr.hhplus.be.server.payment.domain.entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

	PaymentDto findByUserId(Long userId);

}


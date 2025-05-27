 package kr.hhplus.be.server.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.hhplus.be.server.payment.entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments, Long> {

}


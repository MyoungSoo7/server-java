package kr.hhplus.be.server.payment.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.payment.application.dto.CustomerPointHistoryDto;
import kr.hhplus.be.server.payment.domain.entity.CustomerPointHistory;

public interface CustomerPointHistoryRepository extends JpaRepository<CustomerPointHistory, Long> {

	CustomerPointHistoryDto findByCustomerId(long customerId);

}

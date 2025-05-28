package kr.hhplus.be.server.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.payment.dto.CustomerPointHistoryDto;
import kr.hhplus.be.server.payment.entity.CustomerPointHistory;

public interface CustomerPointHistoryRepository extends JpaRepository<CustomerPointHistory, Long> {

	CustomerPointHistoryDto selectPointById(long customerId);

}

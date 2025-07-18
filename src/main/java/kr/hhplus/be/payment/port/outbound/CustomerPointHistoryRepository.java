package kr.hhplus.be.payment.port.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.points.dto.CustomerPointHistoryDto;
import kr.hhplus.be.points.entity.CustomerPointHistory;
import org.springframework.stereotype.Repository;

//리포지토리 및 외부 API 연결 인터페이스를 정의
@Repository
public interface CustomerPointHistoryRepository extends JpaRepository<CustomerPointHistory, Long> {

	CustomerPointHistoryDto findByCustomerId(long customerId);

}

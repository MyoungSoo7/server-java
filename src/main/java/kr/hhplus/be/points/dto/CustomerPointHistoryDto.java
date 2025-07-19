package kr.hhplus.be.points.dto;

import kr.hhplus.be.payment.domain.dto.TransactionType;
import lombok.Data;

@Data
public class CustomerPointHistoryDto {
	private long id;
	private long customerId;
	private long point;
	private TransactionType type;
	private long updateMillis;
}

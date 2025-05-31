package kr.hhplus.be.server.payment.application.dto;

import lombok.Data;

@Data
public class CustomerPointHistoryDto {
	private long id;
	private long customerId;
	private long point;
	private TransactionType type;
	private long updateMillis;
}

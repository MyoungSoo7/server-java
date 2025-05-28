package kr.hhplus.be.server.payment.dto;

import lombok.Data;

@Data
public class CustomerPointHistoryDto {
	private long id;
	private long customerId;
	private long amount;
	private TransactionType type;
	private long updateMillis;
}

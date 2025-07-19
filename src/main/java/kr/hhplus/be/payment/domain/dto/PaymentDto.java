package kr.hhplus.be.payment.domain.dto;

import lombok.Data;

@Data
public class PaymentDto {
	private Long id;
	private Long orderId; // 주문 ID
	private String paymentMethod; // 결제 방법 (예: 신용카드, 계좌이체, 간편결제 등)
	private String paymentStatus; // 결제 상태 (예: 성공, 실패, 대기 중 등)
	private String paymentDate; // 결제 날짜
	private String paymentAmount; // 결제 금액
	private String paymentCreatedAt; // 결제 생성 시간
	private String paymentUpdatedAt; // 결제 수정 시간
}

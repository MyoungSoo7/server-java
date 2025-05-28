package kr.hhplus.be.server.order.dto;

import lombok.Data;

@Data
public class OrdersDto {

	private Long id;
	private Long customerId;
	private Long productId;
	private int quantity;
	private String orderDate;
	private String status; // 예: "주문 접수", "배송 중", "배송 완료", "취소됨"
	private String deliveryAddress;
	private String paymentMethod; // 예: "신용카드", "계좌이체", "간편결제"
	private String deliveryDate; // 예상 배송 날짜
	private String orderCreatedAt; // 주문 생성 시간
	private String orderUpdatedAt; // 주문 수정 시간

}

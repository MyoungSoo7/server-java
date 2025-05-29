package kr.hhplus.be.server.payment.service;

import org.springframework.stereotype.Component;

import kr.hhplus.be.server.order.entity.Orders;

@Component
public class DataPlatformClient {

	public void sendOrderToPlatform(Orders order) {
		// 이곳에 외부 데이터 플랫폼 전송 로직 추가
		// 예: Kafka 또는 RabbitMQ를 통해 데이터 전달
		System.out.println("데이터 플랫폼에 주문 정보 전송: " + order.toString());
	}
}


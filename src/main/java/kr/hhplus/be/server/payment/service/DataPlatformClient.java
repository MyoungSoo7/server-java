package kr.hhplus.be.server.payment.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.order.entity.Orders;

@Component
public class DataPlatformClient {
	private static final String TOPIC = "order_topic";
	private static final String EXCHANGE = "order.exchange";
	private static final String ROUTING_KEY = "order.routingkey";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendOrderToPlatform(Orders order) {
		// Kafka에 메시지 전송
		kafkaTemplate.send(TOPIC, order.toString());
		// RabbitMQ에 메시지 전송
		rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, order.toString());
	}
}


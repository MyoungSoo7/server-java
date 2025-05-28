package kr.hhplus.be.server.order.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

}

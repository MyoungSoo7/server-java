package kr.hhplus.be.server.payment.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.customer.service.CustomerService;
import kr.hhplus.be.server.order.service.OrderService;
import kr.hhplus.be.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final CustomerService customerService;
	private final ProductService productService;
	private final OrderService orderService;
	private final PointService pointService;

	//잔액 충전 / 조회
	public void selectUser(){
		customerService.selectCustomer();

	}


}

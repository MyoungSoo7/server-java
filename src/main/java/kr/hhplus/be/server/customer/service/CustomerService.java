package kr.hhplus.be.server.customer.service;

import org.springframework.stereotype.Service;

import kr.hhplus.be.server.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;



}

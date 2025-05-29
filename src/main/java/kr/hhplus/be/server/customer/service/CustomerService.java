package kr.hhplus.be.server.customer.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.customer.dto.CustomerDto;
import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;

	public CustomerDto getUserById(Long userId) {
		Customers customers = customerRepository.findById(userId).orElseThrow(()
			-> new IllegalArgumentException("Customer not found"));
		return modelMapper.map(customers,CustomerDto.class) ;
	}


}

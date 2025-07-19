package kr.hhplus.be.customer.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.hhplus.be.customer.dto.CustomerDto;
import kr.hhplus.be.customer.entity.Customers;
import kr.hhplus.be.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;

	public CustomerDto getCustomerById(Long userId) {
		Customers customers = customerRepository.findById(userId).orElseThrow(()
			-> new IllegalArgumentException("Customer not found"));
		return modelMapper.map(customers,CustomerDto.class) ;
	}


}

package kr.hhplus.be.server.customer.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	public Optional<Customers> getUserById(Long userId) {
		return customerRepository.findById(userId);
	}

	@Transactional
	public void deductBalance(Long userId, int amount) {
		Customers user = customerRepository.findById(userId)
			.orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
		if (user.getPoint() < amount) {
			throw new IllegalArgumentException("잔액이 부족합니다.");
		}
		user.setPoint(user.getPoint() - amount);
		customerRepository.save(user);
	}


}

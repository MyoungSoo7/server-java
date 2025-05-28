package kr.hhplus.be.server.customer.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kr.hhplus.be.server.customer.entity.Customers;
import kr.hhplus.be.server.customer.repository.CustomerRepository;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	private CustomerRepository customerRepository;


	@Test
	@DisplayName("회원 가입 테스트")
	void signUpTest() {
		//given
		Customers customers = new Customers();




		customerRepository.save(null);
		//when

		//then

	}


}
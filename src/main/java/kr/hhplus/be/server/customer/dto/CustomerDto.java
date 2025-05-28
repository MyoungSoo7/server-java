package kr.hhplus.be.server.customer.dto;

import lombok.Data;

@Data
public class CustomerDto {

	private Long id;
	private String name;
	private String phoneNumber;
	private String address1;
	private String address2;
	private String email;
	private String birthDate;
}

package kr.hhplus.be.customer.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

	private Long id;
	private int point;
	private String name;
	private String phoneNumber;
	private String address1;
	private String address2;
	private String email;
	private String birthDate;
	private String createdAt;
	private String updatedAt;
}

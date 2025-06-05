package kr.hhplus.be.server.payment.domain.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "coupons")
public class Coupons {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private int couponPrice;
	private int couponDiscountRate;
	private int issuedCount;
	private int quota;
	private String couponName;
	private String couponDescription;
	private String couponStatus;
	private String couponType;
	private Date couponStartDate;
	private Date couponEndDate;


}

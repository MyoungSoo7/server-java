package kr.hhplus.be.coupons.entity;

import java.util.Date;

import jakarta.persistence.*;
import kr.hhplus.be.customer.entity.Customers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "userCoupons")
public class UserCoupons{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "customerId", nullable = false)
	private Customers customers; // 사용자 연관 관계

	@ManyToOne
	@JoinColumn(name = "couponId", nullable = false)
	private Coupons coupon; // 쿠폰 연관 관계

	private boolean isUsed;         // 쿠폰 사용 여부
	private Date issueDate;         // 발급 일자
	private Date usageDate;         // 사용 일자
	private Date expiryDate;        // 만료 일자
}

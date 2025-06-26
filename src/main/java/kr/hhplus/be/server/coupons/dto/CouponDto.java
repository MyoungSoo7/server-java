package kr.hhplus.be.server.coupons.dto;

import java.util.Date;

import lombok.Data;

@Data
public class CouponDto {
	private Long id;
	private String couponName;
	private String couponDescription;
	private int couponPrice;
	private int couponDiscountRate;
	private Date couponStartDate;
	private Date couponEndDate;
	private String couponStatus;
}

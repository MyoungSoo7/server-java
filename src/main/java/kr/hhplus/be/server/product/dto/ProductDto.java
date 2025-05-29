package kr.hhplus.be.server.product.dto;

import lombok.Data;

@Data
public class ProductDto {

	private Long id;
	private String productName;
	private String productDescription;
	private String productImageUrl;
	private String productCategory;
	private int productPrice;
	private int productStock;
	private int totalSold;
	private boolean productIsActive;
	private String productCreatedAt;
	private String productUpdatedAt;
}

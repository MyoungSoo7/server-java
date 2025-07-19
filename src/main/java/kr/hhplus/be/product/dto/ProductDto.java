package kr.hhplus.be.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

package kr.hhplus.be.product.controller;

import java.util.List;

import kr.hhplus.be.config.exception.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.product.dto.ProductDto;
import kr.hhplus.be.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;

	// 최근 3일간 가장 많이 팔린 상위 5개 상품 조회
	@GetMapping("/top-selling")
	public ResponseEntity<List<ProductDto>> getTopSellingProducts(
			@RequestParam(defaultValue = "3") int days,
			@RequestParam(defaultValue = "5") int limit
	) {
		List<ProductDto> topSellingProducts = productService.getTopSellingProductsInLast3Days();
		if (topSellingProducts.isEmpty()) {
			throw new ProductNotFoundException("상위 판매 상품이 없습니다.");
		}
		return ResponseEntity.ok(topSellingProducts);
	}
}

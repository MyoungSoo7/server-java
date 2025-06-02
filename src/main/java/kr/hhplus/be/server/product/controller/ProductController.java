package kr.hhplus.be.server.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.product.dto.ProductDto;
import kr.hhplus.be.server.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

	private final ProductService productService;

	// 최근 3일간 가장 많이 팔린 상위 5개 상품 조회
	@GetMapping("/top-selling")
	public ResponseEntity<List<ProductDto>> getTopSellingProducts() {
		List<ProductDto> topSellingProducts = productService.getTopSellingProductsInLast3Days();
		return ResponseEntity.ok(topSellingProducts);
	}
}

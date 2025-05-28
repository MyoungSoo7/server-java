package kr.hhplus.be.server.product.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.product.dto.ProductDto;
import kr.hhplus.be.server.product.entity.Products;
import kr.hhplus.be.server.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;

	public ProductDto selectProductById(long productId) {
		return getProductDto( productId);
	}

	private ProductDto getProductDto(Long productId) {
		Products product = productRepository.findById(productId).orElseThrow(() ->
			new IllegalArgumentException("Product not found: " + productId)
		);
		return modelMapper.map(product, ProductDto.class); // ModelMapper로 변환
	}

	public List<ProductDto> selectAllProducts() {
		List<Products> products = productRepository.findAll();
		return products.stream().map(product ->
			modelMapper.map(product, ProductDto.class))
			.collect(Collectors.toList()); 
	}

	
	// 재고는 0개 이하 불가
	public void updateProductStock(long productId, int updatedStock) {
		Products product = productRepository.findById(productId)
			.orElseThrow(() -> new IllegalArgumentException("상품을 찾을 수 없습니다. ID: " + productId));

		// 기존 재고 확인
		int currentStock = product.getProductStock();

		// 재고 감소로 인해 기존 재고가 0개 이하로 내려가는 경우 정책 위반
		if (currentStock + updatedStock < 0) {
			throw new IllegalArgumentException("현재 재고는 0개 이하로 떨어질 수 없습니다. 현재 재고: "
				+ currentStock + ", 감소하려는 재고: " + (-updatedStock));
		}

		// 재고 업데이트
		product.setProductStock(currentStock + updatedStock);
		productRepository.save(product); // 변경 사항 저장
	}



}

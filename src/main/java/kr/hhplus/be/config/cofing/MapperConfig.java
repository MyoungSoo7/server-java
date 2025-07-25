package kr.hhplus.be.config.cofing;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MapperConfig 클래스
 * ModelMapper를 Bean으로 등록하여 객체 간의 매핑을 지원합니다.
 * 이 설정은 DTO와 엔티티 간의 변환을 용이하게 합니다.
 */
@Configuration
class MapperConfig {
	/**
	 * ModelMapper Bean 생성
	 * 객체 간의 매핑을 위한 설정을 포함
	 *
	 * @return ModelMapper 인스턴스
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}

package kr.hhplus.be.server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

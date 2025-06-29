package kr.hhplus.be.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

	/*주문 생성(상품, 고객, 수량 등 필수 정보 검증)
	결제 시 포인트 차감 및 부족 시 예외 처리
	쿠폰 적용 시 할인 및 사용 이력 검증
	트랜잭션 처리(주문-결제-쿠폰 사용 일관성)
	동시성 문제(포인트/쿠폰 중복 사용 방지)*/

	// 6주차
	/*Redis 기반의 분산락을 직접 구현해보고 동작에 대한 통합테스트 작성
	주문/예약/결제 기능 등에 (1) 적절한 키 (2) 적절한 범위를 선정해 분산락을 적용
	주요 평가 기준
	분산락에 대한 이해와 DB Tx 과 혼용할 때 주의할 점을 이해하였는지
	적절하게 분산락이 적용되는 범위에 대해 구현을 진행하였는지
	과제 내용
조회가 오래 걸리거나, 자주 변하지 않는 데이터 등 애플리케이션의 요청 처리 성능을 높이기 위해 캐시 전략을 취할 수 있는 구간을 점검하고, 적절한 캐시 전략을 선정
위 구간에 대해 Redis 기반의 캐싱 전략을 시나리오에 적용하고 성능 개선 등을 포함한 보고서 작성 및 제출
	*/
	
	// 7주차
	//가장 많이 주문한 상품 랭킹을 Redis 기반으로 개발하고 설계 및 구현
	//선착순 쿠폰발급 기능에 대해 Redis 기반의 설계를 진행하고, 적절하게 동작할 수 있도록 쿠폰 발급 로직을 개선해 제출
}

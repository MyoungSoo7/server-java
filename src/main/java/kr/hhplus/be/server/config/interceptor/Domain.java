package kr.hhplus.be.server.config.interceptor;

/*
 * 1. MockMessageProducer: 테스트 환경에서 메시지 전송을 모킹하여 실제 Kafka 의존성을 제거
 * 2. Outbox 테이블: 데이터베이스에 메시지를 저장하고, 별도의 프로세스가 이를 읽어 Kafka로 전송
 *
 * 이 두 가지 방법은 Kafka 의존성을 줄이고, 테스트 용이성을 높이며, 시스템의 복잡성을 감소시킵니다.
 */
public class Domain {
}

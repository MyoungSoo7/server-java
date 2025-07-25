package kr.hhplus.be.config.error;

/**
 * ErrorResponse 클래스
 * API에서 발생하는 에러에 대한 응답 형식을 정의합니다.
 * code: 에러 코드
 * message: 에러 메시지
 */
public record ErrorResponse(
        String code,
        String message
) {
}

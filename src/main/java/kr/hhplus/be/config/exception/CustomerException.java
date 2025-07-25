package kr.hhplus.be.config.exception;

/**
 * CustomerException 클래스
 * 사용자 정의 예외로, 고객 관련 작업에서 발생할 수 있는 예외를 처리합니다.
 * 이 예외는 RuntimeException을 상속받아 사용됩니다.
 */
public class CustomerException extends RuntimeException {
    public CustomerException(String message) {
        super(message);
    }
    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }
}


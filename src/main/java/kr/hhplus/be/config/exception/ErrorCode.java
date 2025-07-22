package kr.hhplus.be.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


/**
 * ErrorCode Enum
 * 다양한 에러 코드와 메시지를 정의하여 일관된 에러 응답을 제공합니다.
 * 각 에러 코드는 HTTP 상태 코드와 관련된 메시지를 포함합니다.
 */
@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    PRODUCT_CAN_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not founded"),
        ;
    private final HttpStatus status;
    private final String message;

}

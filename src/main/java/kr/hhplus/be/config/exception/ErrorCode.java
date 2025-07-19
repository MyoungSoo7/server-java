package kr.hhplus.be.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "Invalid password"),
    PRODUCT_CAN_NOT_FOUND(HttpStatus.NOT_FOUND, "Product not founded"),
        ;
    private final HttpStatus status;
    private final String message;

}

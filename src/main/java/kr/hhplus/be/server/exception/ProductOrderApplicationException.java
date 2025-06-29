package kr.hhplus.be.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductOrderApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;
    /**
     * 생성자
     *
     * @param errorCode 에러 코드
     * @param message   에러 메시지
     */
    public ProductOrderApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = null;
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        } else {
            return String.format("%s. %s", errorCode.getMessage(), message);
        }

    }
}

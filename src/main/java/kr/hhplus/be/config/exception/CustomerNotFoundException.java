package kr.hhplus.be.config.exception;

public class CustomerNotFoundException extends CustomerException {
    public CustomerNotFoundException(Long id) {
        super("고객을 찾을 수 없습니다. id: " + id);
    }
    public CustomerNotFoundException(Integer id) {
        super("고객을 찾을 수 없습니다. id: " + id);
    }
}

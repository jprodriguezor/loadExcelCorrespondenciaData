package co.com.foundation.soaint.infrastructure.exceptions;

public class BusinessException extends Exception {

    public BusinessException(String s) {
        super(s);
    }

    public BusinessException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

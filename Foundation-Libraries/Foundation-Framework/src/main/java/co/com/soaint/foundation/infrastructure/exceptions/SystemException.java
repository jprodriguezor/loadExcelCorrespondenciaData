package co.com.soaint.foundation.infrastructure.exceptions;

public class SystemException extends Exception {

    public SystemException(String s) {
        super(s);
    }

    public SystemException(String s, Throwable throwable) {
        super(s, throwable);
    }
}

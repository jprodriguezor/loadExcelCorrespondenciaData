package co.com.foundation.soaint.infrastructure.exceptions;

import co.com.foundation.soaint.infrastructure.common.MessageUtil;

public class ExceptionBuilder {

    private String message;
    private Throwable rootException;

    private ExceptionBuilder() {
    }

    public static ExceptionBuilder newBuilder() {
        return new ExceptionBuilder();
    }

    public ExceptionBuilder withMessage(String message) {
        this.message = MessageUtil.getMessage(message);
        return this;
    }

    public ExceptionBuilder withRootException(Throwable rootException) {
        this.rootException = rootException;
        return this;
    }

    public BusinessException buildBusinessException() {
        return new BusinessException(message, rootException);
    }

    public SystemException buildSystemException() {
        return new SystemException(message, rootException);
    }

}

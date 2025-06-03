package com.papas;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApplicationException extends RuntimeException {

    private Errors error;

    public ApplicationException() {
        super();
    }

    public ApplicationException(Errors error) {
        super(error.name());
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

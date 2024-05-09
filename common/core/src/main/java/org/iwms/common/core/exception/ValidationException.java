package org.iwms.common.core.exception;

/**
 * @author leung
 */
public class ValidationException extends BaseException{

    public ValidationException() {
        super();
    }

    public ValidationException(ErrorType errorType, String message) {
        super(errorType, message);
    }

    public ValidationException(ErrorType errorType, String message, Throwable cause) {
        super(errorType, message, cause);
    }

}

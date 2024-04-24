package org.iwms.objectstorage.exception;


import lombok.extern.slf4j.Slf4j;
import org.iwms.common.core.entity.vo.Result;
import org.iwms.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @author leung
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {MinioFileException.class})
    public Result uploadFileFailure(MinioFileException ex) {
        log.error(ex.getMessage());
        return Result.fail(ex.getErrorType());
    }
}

package org.iwms.objectstorage.exception;

import org.iwms.common.core.exception.BaseException;
import org.iwms.common.core.exception.ErrorType;

/**
 * @author leung
 */
public class MinioFileException extends BaseException {
    public MinioFileException() {
        super(MinioFileType.FILE_UPLOAD_FAILED);
    }

    public MinioFileException(String message) {
        super(MinioFileType.FILE_UPLOAD_FAILED, message);
    }

    public MinioFileException(ErrorType errorType, String message) {
        super(errorType, message);
    }
}

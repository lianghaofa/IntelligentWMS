package org.iwms.objectstorage.exception;

import lombok.Getter;

import org.iwms.common.core.exception.ErrorType;


/**
 * @author leung
 */
@Getter
public enum MinioFileType implements ErrorType {
    FILE_UPLOAD_FAILED("040100", "文件上传失败！"),
    FILE_REMOVE_FAILED("040200", "文件删除失败！"),
    FILE_GET_FAILED("040300", "获取文件失败！"),
    FAILED_DOWNLOAD_FILE("040400", "下载文件失败！")

    ;
    /**
     * 错误类型码
     */
    private String code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    MinioFileType(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

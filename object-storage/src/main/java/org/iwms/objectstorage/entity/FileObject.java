package org.iwms.objectstorage.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


/**
 * @author leung
 */
@ApiModel
@Data
public class FileObject {
    @NotBlank(message = "不能为空")
    @ApiModelProperty("文件名")
    private String fileName;
    @NotBlank(message = "不能为空")
    @ApiModelProperty("存储空间(桶)")
    private String bucketName;
}

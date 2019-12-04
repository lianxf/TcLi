package cn.likepeng.commons.core.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.io.Serializable;

@ApiModel("统一响应体")
@Data
public class Response<T> implements Serializable {

    @ApiModelProperty(value = "状态码 200表示成功",dataType = "int",example = "200")
    private Integer code = 200;

    @ApiModelProperty(value = "错误信息",dataType = "string",example = "SUCCESS")
    private String msg = "SUCCESS";

    @ApiModelProperty(value = "响应数据")
    private T data ;
}
package cn.likepeng.commons.core.exception;


public class BusinessException  extends Exception{

    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
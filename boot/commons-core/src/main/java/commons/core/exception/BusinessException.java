package commons.core.exception;


import lombok.Data;

@Data
public class BusinessException extends Exception{

    private Integer code;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
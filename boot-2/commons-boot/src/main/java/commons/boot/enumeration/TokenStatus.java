package commons.boot.enumeration;

import lombok.Getter;

@Getter
public enum TokenStatus {
    NORMAL(800,"正常"),
    INVALID(801,"无效Token"),
    EXPIRE(802,"Token已过期");


    private Integer state;
    private String stateDesc;

    TokenStatus(Integer state, String stateDesc) {
        this.state     = state;
        this.stateDesc = stateDesc;
    }
}

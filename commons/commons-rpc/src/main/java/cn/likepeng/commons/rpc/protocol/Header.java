package cn.likepeng.commons.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Header {
    //消息校验码  0xABEF(固定值表示netty协议)+主版本(1-255)+次版本(1-255)
    private Integer crCode = 0xABEF0101;

    //消息长度
    private Integer length;

    //会话Id
    private String sessionId;

    private Byte type;

    //消息优先级
    private Byte priority;

    //可选字段 用于扩展消息头
    private Map<String,Object> attachment;
}

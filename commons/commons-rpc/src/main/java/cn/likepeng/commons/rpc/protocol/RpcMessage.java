package cn.likepeng.commons.rpc.protocol;

import lombok.Data;

@Data
public class RpcMessage {
    private Header header;
    private Object body;
}

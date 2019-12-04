package cn.likepeng.commons.rpc.utils;

public class IdUtil {
    public static long snowflakeId(){
        return cn.hutool.core.util.IdUtil.createSnowflake(1,1).nextId();
    }

    public static long snowflakeId(Integer workerId,Integer centerId){
        return cn.hutool.core.util.IdUtil.createSnowflake(workerId,centerId).nextId();
    }

    public static String snowflakeStringId(){
        return cn.hutool.core.util.IdUtil.createSnowflake(1,1).nextIdStr();
    }

    public static String snowflakeStringId(Integer workerId,Integer centerId){
        return cn.hutool.core.util.IdUtil.createSnowflake(workerId,centerId).nextIdStr();
    }

    public static String simpleUUID(){
        return cn.hutool.core.util.IdUtil.fastSimpleUUID();
    }

    public static String UUID(){
        return cn.hutool.core.util.IdUtil.fastUUID();
    }

    public static String objectId(){
        return cn.hutool.core.util.IdUtil.objectId();
    }
}

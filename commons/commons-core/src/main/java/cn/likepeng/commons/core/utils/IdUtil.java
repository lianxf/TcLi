package cn.likepeng.commons.core.utils;


@SuppressWarnings("all")
public class IdUtil {

    private static SnowflakeIdWorker snowflake = null;

    public static long snowflakeId(){
        if (snowflake == null){
            synchronized (IdUtil.class){
                if (snowflake ==null){
                    snowflake = new SnowflakeIdWorker(0,0);
                }
            }
        }
        return snowflake.nextId();
    }

    public static long snowflakeId(Integer workerId,Integer centerId){
        if (snowflake == null){
            synchronized (IdUtil.class){
                if (snowflake ==null){
                    snowflake = new SnowflakeIdWorker(workerId,centerId);
                }
            }
        }
        return snowflake.nextId();
    }

    public static String snowflakeStringId(){
        if (snowflake == null){
            synchronized (IdUtil.class){
                if (snowflake ==null){
                    snowflake = new SnowflakeIdWorker(0,0);
                }
            }
        }
        return snowflake.nextIdStr();
    }

    public static String snowflakeStringId(Integer workerId,Integer centerId){
        if (snowflake == null){
            synchronized (IdUtil.class){
                if (snowflake ==null){
                    snowflake = new SnowflakeIdWorker(workerId,centerId);
                }
            }
        }
        return snowflake.nextIdStr();
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

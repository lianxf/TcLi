package cn.likepeng.commons.rpc.utils;

import lombok.Data;
import org.junit.Test;
import static org.junit.Assert.*;

public class SerializerUtilTest {

    @Test
    public void protoStuffSerialize() {
        byte[] bytes = SerializerUtil.protoStuffSerialize(new Person());
        Person person = SerializerUtil.protoStuffDeserialize(bytes, Person.class);
        System.out.println(person);
    }
}

@Data
class Person{
    private String name = "张三";
    private String sex = "男";
}
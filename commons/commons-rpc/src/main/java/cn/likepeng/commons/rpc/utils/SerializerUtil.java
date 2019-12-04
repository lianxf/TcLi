package cn.likepeng.commons.rpc.utils;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class SerializerUtil{
    private static final ThreadLocal<LinkedBuffer> BUFFERS = new ThreadLocal();

    private static final Schema<SerializeDeserializeWrapper> WRAPPER_SCHEMA = RuntimeSchema.getSchema(SerializeDeserializeWrapper.class);


    public static byte[] protoStuffSerialize(Object obj) {
        Class<?> clazz = (Class<?>) obj.getClass();
        LinkedBuffer buffer = BUFFERS.get();
        if (buffer == null) {
            buffer = LinkedBuffer.allocate(1024);
            BUFFERS.set(buffer);
        }
        try {
            Object serializeObject = obj;
            Schema schema = WRAPPER_SCHEMA;
            if (clazz.isArray() || Collection.class.isAssignableFrom(clazz)
                    || Map.class.isAssignableFrom(clazz) || Set.class.isAssignableFrom(clazz)) {//Protostuff 不支持序列化/反序列化数组、集合等对象,特殊处理
                serializeObject = SerializeDeserializeWrapper.builder(obj);
            } else {
                schema = RuntimeSchema.getSchema(clazz);
            }
            return ProtostuffIOUtil.toByteArray(serializeObject, schema, buffer);
        } finally {
            buffer.clear();
        }
    }

    public static <T> T protoStuffDeserialize(byte[] data,Class<T> clazz) {
        if (clazz.isArray() || Collection.class.isAssignableFrom(clazz)
                || Map.class.isAssignableFrom(clazz) || Set.class.isAssignableFrom(clazz)) {//Protostuff 不支持序列化/反序列化数组、集合等对象,特殊处理
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
            ProtostuffIOUtil.mergeFrom(data, wrapper, WRAPPER_SCHEMA);
            return wrapper.getData();
        } else {
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            T message = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        }
    }

    static class SerializeDeserializeWrapper<T> {

        private T data;

        public static <T> SerializeDeserializeWrapper<T> builder(T data) {
            SerializeDeserializeWrapper<T> wrapper = new SerializeDeserializeWrapper<>();
            wrapper.setData(data);
            return wrapper;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }


    public static<T> byte[] kryoSerialize(T data){
        Kryo kryo = new Kryo();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Output output = new Output(outputStream);
        kryo.writeObject(output, data);
        output.flush();
        return outputStream.toByteArray();
    }

    public static<T> T kryoDeserialize(byte[] bytes,Class<T> clazz){
        Kryo kryo = new Kryo();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        Input input = new Input(byteArrayInputStream);
        return kryo.readObject(input, clazz);
    }
}

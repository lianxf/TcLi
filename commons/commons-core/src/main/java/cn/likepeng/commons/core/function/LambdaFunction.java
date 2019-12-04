package cn.likepeng.commons.core.function;

import java.util.function.Consumer;

@SuppressWarnings("all")
public class LambdaFunction {

    public static <T,E extends Throwable> Consumer<T> consumerWapper(ThrowingConsumer<T,E> throwingConsumer){
        return consumer ->{
            try {
                throwingConsumer.apply(consumer);
            } catch (Throwable e) {

            }
        };
    }


    @FunctionalInterface
    public static interface ThrowingConsumer<T,E extends Throwable> {
        void apply(T t) throws E;
    }
}

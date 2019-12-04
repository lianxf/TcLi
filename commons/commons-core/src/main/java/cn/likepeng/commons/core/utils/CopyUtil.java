package cn.likepeng.commons.core.utils;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.beans.BeanMap;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@SuppressWarnings("all")
public class CopyUtil {

    private static final Map<String, BeanCopier> BEAN_COPIER_BUFFER = new ConcurrentHashMap<String, BeanCopier>();

    private static final Objenesis OBJENESIS = new ObjenesisStd();

    public static<S,T> List<T> copyCollection(S source, Class<T> target){
        ArrayList<T> ts = new ArrayList<>();
        if (source instanceof Collection){
            Collection sourceCollection = (Collection) source;
            Iterator iterator = sourceCollection.iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                T t = OBJENESIS.newInstance(target);
                copy(next,t);
                ts.add(t);
            }
        }
        return ts;
    }

    public static<S,T> void copy(S source,T target){
        String key = genKey(source.getClass(), target.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIER_BUFFER.containsKey(key)) {
            copier = BeanCopier.create(source.getClass(), target.getClass(), true);
            BEAN_COPIER_BUFFER.put(key, copier);
        } else {
            copier = BEAN_COPIER_BUFFER.get(key);
        }
        copier.copy(source, target, (value, t, context)->{
            if(value == null) {
                return BeanMap.create(target).get(getPropertyName(String.valueOf(context)));
            }
            return value;
        });
    }

    private static String genKey(Class<?> sourceClazz, Class<?> targetClazz) {
        return sourceClazz.getName() + targetClazz.getName();
    }

    private static String getPropertyName(String methodName) {
        char[] newChar = new char[methodName.length() - 3];
        System.arraycopy(methodName.toCharArray(), 3, newChar, 0, methodName.length() - 3);
        newChar[0] = Character.toLowerCase(newChar[0]);
        return String.valueOf(newChar);
    }
}

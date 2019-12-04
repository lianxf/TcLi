package cn.likepeng.commons.core.utils;

import cn.hutool.core.util.StrUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtil extends StrUtil {

    public static String toBigCamelCase(String data){
        String result = upperFirst(toCamelCase(data));
        return result;
    }

    public static String toBigUnderlineCase(String data){
        String result = toUnderlineCase(data).toUpperCase();
        return result;
    }

    public static String toBigSymbolCase(String camelCaseString,char concatenationCharacter){
        String result = toSymbolCase(camelCaseString,concatenationCharacter).toUpperCase();
        return result;
    }

    public static Map<String,String> paramsToMap(String url){
        String s = subAfter(url, "?", true);
        List<String> split = split(s, '&');
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < split.size() ; i++) {
            String kv = split.get(i);
            String k = subBefore(kv, '=', true);
            String v = subAfter(kv, '=', true);
            map.put(k,v);
        }
        return map;
    }
}

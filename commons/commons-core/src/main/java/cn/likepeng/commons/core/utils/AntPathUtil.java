package cn.likepeng.commons.core.utils;

import cn.likepeng.commons.core.utils.ant.AntPathMatcher;

public class AntPathUtil {
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static Boolean matchPath(String pattern,String path){
        return antPathMatcher.match(pattern,path);
    }

    public static Boolean matchPathAndMethod(String pathPattern,String path,String methodPattern,String method){
        boolean matchPath = antPathMatcher.match(pathPattern, path);
        boolean matchMethod = methodPattern.toUpperCase().equals(method.toUpperCase());
        return matchPath && matchMethod;
    }
}

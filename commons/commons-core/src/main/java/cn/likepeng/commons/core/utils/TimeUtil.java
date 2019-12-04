package cn.likepeng.commons.core.utils;

import cn.hutool.core.date.DateUtil;
import cn.likepeng.commons.core.enumeration.TimePattern;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil extends DateUtil {

    public static String getNowString(){
        return getDateString(new Date());
    }

    public static String getDateString(Date date){
        return getDateString(date, TimePattern.SECOND_PATTERN);
    }

    public static String getDateString(Date date, TimePattern timePattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern.getValue());
        String result = simpleDateFormat.format(date);
        return result;
    }
}

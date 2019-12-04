package cn.likepeng.commons.core.enumeration;

import lombok.Getter;

@Getter
public enum TimePattern {
    NORMAL_PATTERN("yyyy-MM-dd"),
    HOUR_PATTERN("yyyy-MM-dd HH"),
    MINUTE_PATTERN("yyyy-MM-dd HH:mm"),
    SECOND_PATTERN("yyyy-MM-dd HH:mm:ss"),
    MILLISECOND_PATTERN("yyyy-MM-dd HH:mm:ss:SSS");

    private String value;

    TimePattern(String pattern) {
        this.value = pattern;
    }
}

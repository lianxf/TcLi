package commons.core.regex;

import lombok.Getter;

@Getter
public enum NormalRegex {

    NUMBER("^\\d+$"),//数字
    POSITIVE_INTEGER("^[1-9]\\d*$"),//正整数
    NEGATIVE_INTEGER("^-[1-9]\\d*$"),//负整数
    FLOATING_NUMBER("^(-?\\d+)(\\.\\d+)?$"),//浮点数
    POSITIVE_FLOATING_NUMBER("^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$"),//正浮点数
    NEGATIVE_FLOATING_NUMBER("^-([1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*)$"),//负浮点数

    CHINESE_CHARACTER("^[\\u4e00-\\u9fa5]{0,}$"),//汉字
    ENGLISH_CHARACTER("^[A-Za-z]+$"),//英文字符
    ENGLISH_UPPER_CHARACTER("^[a-z]+$"),//小写英文字符
    ENGLISH_LOWER_CHARACTER("^[A-Z]+$"),//大写英文字符
    ENGLISH_NUMBER("^[A-Za-z0-9]+$"),//英文和数字
    ENGLISH_NUMBER_UNDERLINE("^\\w+$"),//英文数字和下划线
    CHINESE_ENGLISH_NUMBER("^[\\u4E00-\\u9FA5A-Za-z0-9]+$"),//中文英文数字
    CHINESE_ENGLISH_NUMBER_UNDERLINE("^[\\u4E00-\\u9FA5A-Za-z0-9_]+$"),//中文英文数字和下划线

    EMAIL("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$"),//邮箱
    MOBILE("^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\\d{8}$"),//手机号
    PHONE("^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$"),//固定电话
    ;
    String regex;

    NormalRegex(String regex) {
        this.regex = regex;
    }
}

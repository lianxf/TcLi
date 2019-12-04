package cn.likepeng.commons.core.utils;

import cn.hutool.core.lang.Validator;
import cn.likepeng.commons.core.regex.NormalRegex;

@SuppressWarnings("all")
public class ValidateUtil {

    /* 是否是数字 */
    public static Boolean isNumber(String data) {
        return Validator.isNumber(data);
    }

    /* 是否是正整数 */
    public static Boolean isPositiveNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.POSITIVE_INTEGER.getRegex(), data);
    }

    /* 是否是负整数 */
    public static Boolean isNegativeNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.NEGATIVE_INTEGER.getRegex(), data);
    }

    /* 是否是浮点数 */
    public static Boolean isFloatingNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.FLOATING_NUMBER.getRegex(), data);
    }

    /* 是否是正浮点数 */
    public static Boolean isPositiveFloatingNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.POSITIVE_FLOATING_NUMBER.getRegex(), data);
    }

    /* 是否是负浮点数 */
    public static Boolean isNegativeFloatingNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.NEGATIVE_FLOATING_NUMBER.getRegex(), data);
    }

    /*邮箱*/
    public static Boolean isEmail(String data) {
        return Validator.isEmail(data);
    }

    /*是否为汉字*/
    public static Boolean isChineseCharacter(String data) {
        return  Validator.isMactchRegex(NormalRegex.CHINESE_CHARACTER.getRegex(), data);
    }

    /*是否为英文*/
    public static Boolean isEnglishCharacter(String data) {
        return Validator.isMactchRegex(NormalRegex.ENGLISH_CHARACTER.getRegex(), data);
    }

    /*是否为大写英文*/
    public static Boolean isEnglishUpperCharacter(String data) {
        return Validator.isMactchRegex(NormalRegex.ENGLISH_UPPER_CHARACTER.getRegex(), data);
    }

    /*是否为小写英文*/
    public static Boolean isEnglishLowerCharacter(String data) {
        return Validator.isMactchRegex(NormalRegex.ENGLISH_LOWER_CHARACTER.getRegex(), data);
    }

    /*是否为英文加数字*/
    public static Boolean isEnglishCharacterAndNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.ENGLISH_NUMBER.getRegex(), data);
    }

    /*是否为英文数字汉字*/
    public static Boolean isChineseEnglishCharacterAndNumber(String data) {
        return Validator.isMactchRegex(NormalRegex.CHINESE_ENGLISH_NUMBER.getRegex(), data);
    }

    /*是否为英文数字加下划线*/
    public static Boolean isEnglishCharacterNumberUnderline(String data) {
        return Validator.isMactchRegex(NormalRegex.ENGLISH_NUMBER_UNDERLINE.getRegex(), data);
    }

    /*是否为英文数字汉字加下划线*/
    public static Boolean isChineseEnglishCharacterNumberUnderline(String data) {
        return Validator.isMactchRegex(NormalRegex.CHINESE_ENGLISH_NUMBER_UNDERLINE.getRegex(), data);
    }

    /*是否是手机号*/
    public static Boolean isMobile(String data){
        return Validator.isMobile(data);
    }

    /*是否是电话号(座机)*/
    public static Boolean isPhone(String data){
        return Validator.isMactchRegex(NormalRegex.PHONE.getRegex(), data);
    }

   /*身份证*/
    public static Boolean isIdCardNum(String data){
        return Validator.isCitizenId(data);
    }

    /*对象为空*/
    public static Boolean isEmpty(Object object) {
        return Validator.isEmpty(object);
    }

    /*正则表达式*/
    public static Boolean isMatchRegex(String data, String reg) {
        return Validator.isMactchRegex(reg, data);
    }

    /*字母开头数字下划线名字*/
    public static Boolean englishNumberUnderlineName(String name, Integer min, Integer max){
        String reg = "^[a-zA-Z][a-zA-Z0-9_]{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, name);
    }

    /*字母开头数字下划线名字*/
    public static Boolean englishNumberUnderlineName(String name, String min, String max){
        String reg = "^[a-zA-Z][a-zA-Z0-9_]{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, name);
    }

    /*中文字母数字下划线名字*/
    public static Boolean chineseEnglishNumberUnderlineName(String name,Integer min,Integer max){
        String reg = "[\\u4E00-\\u9FA5A-Za-z0-9_]{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, name);
    }

    /*中文字母数字下划线名字*/
    public static Boolean chineseEnglishNumberUnderlineName(String name,String min,String max){
        String reg = "[\\u4E00-\\u9FA5A-Za-z0-9_]{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, name);
    }

    /*普通密码*/
    public static Boolean normalPassword(String password,Integer min,Integer max){
        String reg = "^[a-zA-Z]\\w{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, password);
    }

    /*普通密码*/
    public static Boolean normalPassword(String password,String min,String max){
        String reg = "^[a-zA-Z]\\w{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, password);
    }

    /*强密码*/
    public static Boolean strongPassword(String password,Integer min,Integer max){
        String reg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, password);
    }

    /*强密码*/
    public static Boolean strongPassword(String password,String min,String max){
        String reg = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{"+min+","+max+"}$";
        return Validator.isMactchRegex(reg, password);
    }

    /*钱*/
    public static Boolean isMoney(String money,Integer max){
        String reg = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,"+max+"})?$";
        return Validator.isMactchRegex(reg, money);
    }

    /*钱*/
    public static Boolean isMoney(String money,String max){
        String reg = "^([0-9]+|[0-9]{1,3}(,[0-9]{3})*)(.[0-9]{1,"+max+"})?$";
        return Validator.isMactchRegex(reg, money);
    }


    public static Boolean hasParms(String url){
        int i = StringUtil.lastIndexOfIgnoreCase(url, "?");
        if (i==-1){
            return false;
        }
        String s = StringUtil.subAfter(url, "?", true);
        int hasEq = StringUtil.indexOf(url, '=');
        if (hasEq == -1){
            return false;
        }
        return true;
    }
}

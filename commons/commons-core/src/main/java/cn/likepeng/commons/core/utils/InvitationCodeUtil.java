package cn.likepeng.commons.core.utils;

import java.util.Random;

public class InvitationCodeUtil {

    private static final char[] r = new char[]{
            'a', 'u', 'q', 'w', 'e', '8',  's', '2',
            'x', '9', 'c', '7', 'p', '5', 'i', 'k',
            'm', 'j',  'f', 'r', '4', 'v', 'y', 'd',
            't', 'n', '6', 'b', 'g', 'h', '3', 'z','H'
    };
    private static final char b='A'; /** (不能与自定义进制有重复) */
    private static final int binLen = r.length;/** 进制长度 */
    private static final int s =6;/** 序列最小长度 */
    /**
     * 更加id 生产6为随机码
     * @param id
     * @return
     */
    public static String generate(Long id){
        char[] buf = new char[32];
        int charPos = 32;
        while((id/binLen)>0){
            int intid = (int) (id%binLen);
            buf[--charPos] = r[intid];
            id/=binLen;
        }
        String str = new String(buf,charPos,(32-charPos));
        //不够长度的自动随机补全
        if(str.length()<s){
            StringBuilder sb = new StringBuilder();
            sb.append(b);
            Random random = new Random();
            for(int i=1;i<s-str.length();i++){
                sb.append(r[random.nextInt(binLen)]);
            }
            str+=sb.toString();
        }
        return str;
    }
}

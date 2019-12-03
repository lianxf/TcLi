package commons.boot.enable.share;

import org.redisson.api.RAtomicLong;
import org.redisson.api.RedissonClient;

public class ShareCodeService {
    private EnableShareCodeService enableShareCodeService;

    private RedissonClient shareCodeRedisClient;

    /**   53的2次方   */
    private Double secondPlace  = Math.pow(53,2);

    /**   53的3次方   */
    private  Double thirdPlace   = Math.pow(53,3);

    /**   53的4次方   */
    private  Double fourthPlace  = Math.pow(53,4);

    /**   53的5次方   */
    private  Double fifthPlace   = Math.pow(53,5);

    /**   53的6次方   */
    private  Double sixthPlace   = Math.pow(53,6);

    /**   六位邀请码左数第一个值取值范围   */
    private  final String[]  CODES6 = {"H","B","b","7","k","c","z","p","n",
            "Q","a","S","r","j","R","s",
            "m","3","N","h","e","9","f",
            "E","q","L","t","u","A","F",
            "J","5","P","K","4","V","G",
            "v","X","6","T","M","D","W",
            "U","d","8","Z","y","Y","2","g","C"};

    /**   六位邀请码左数第二个值取值范围   */
    private  final String[]  CODES5 = {"C","A","F","n","z","M","B","V","b",
            "s","Q","m","3","S","r","j",
            "f","G","L","e","J","9","a",
            "t","N","q","u","k","c","E",
            "Z","h","5","Y","H","v","T",
            "4","R","X","K","p","7","y",
            "6","8","U","d","2","W","D","g","P"};

    /**   六位邀请码左数第三个值取值范围   */
    private  final String[]  CODES4 = {"V","b","C","z","M","k","c","9","R",
            "Q","a","S","r","j","J","A",
            "m","3","N","e","F","n","2",
            "7","W", "E","q","L","t","u",
            "h" ,"5","H","v","4","Y","D",
            "X","T","6","B","p","Z","f",
            "g","U","d","8","s","P","K","G","y"};

    /**   六位邀请码右数第三个值取值范围   */
    private  final String[]  CODES3 = {"6","G","f","B","7","c","J","9","2",
            "Q","3","N","R","s","V","b",
            "m","W","t","u","k","e","a",
            "E","K","4","S","q","L","A",
            "P","5","F","h","n","M","Y",
            "D","C","r","j","H","v","X",
            "g","U","d","8","Z","y","z","T","p",};

    /**   六位邀请码右数第二个值取值范围   */
    private  final String[]  CODES2 = {"3","m","V","b","X","6","f","B","7",
            "c","Q","G","R","s","W","N",
            "t","u","k","a","S","e","J",
            "5","L","F","A","9","E","q",
            "h","4","K","P","n","Z","D",
            "r","p","j","v","H","T","2",
            "g", "U","M","d","8","z","Y","C","y"};

    /**   六位邀请码右数第一个值取值范围   */
    private  final String[]  CODES1 = {"e","a","f","B","C","y","7","c","6",
            "Q","3","n","R","m","N","F",
            "W","t","u","k","S","J","9",
            "L","g","A","5","z","P","E",
            "h","K","4","T","p","q","G",
            "r","H","v","s","V","b","X",
            "U","j","M","Z","D","d","8","Y","2"};

    public ShareCodeService(EnableShareCodeService enableShareCodeService, RedissonClient shareCodeRedisClient) {
        this.enableShareCodeService = enableShareCodeService;
        this.shareCodeRedisClient = shareCodeRedisClient;
    }


    public  String getShareCode(){

        RAtomicLong shareIndex = shareCodeRedisClient.getAtomicLong("shareIndex");

        if (!shareIndex.isExists()){
            shareIndex.set(0);
        }
        long index = shareIndex.getAndIncrement();

        int code1Index = (int) (index % 53);

        int code2Index = (int) (index % secondPlace/53);

        int code3Index = (int) (index % thirdPlace /secondPlace);

        int code4Index = (int) (index % fourthPlace / thirdPlace);

        int code5Index = (int) (index % fifthPlace /fourthPlace);

        int code6Index = (int) (index % sixthPlace / fifthPlace);

        String code = CODES6[code6Index] + CODES5[code5Index] + CODES4[code4Index] + CODES3[code3Index] + CODES2[code2Index] + CODES1[code1Index];

        return code;
    }
}

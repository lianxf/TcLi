package cn.likepeng.commons.core.utils.gmap;

import lombok.Data;
import java.io.Serializable;

@Data
public class IPInfo implements Serializable {

    private String province = "未知";

    private String city = "未知";

    /**  邮编   */
    private String adcode = "未知";

    /**  坐标   */
    private String rectangle = "未知";
}

package cn.likepeng.commons.core.utils.gmap;

import lombok.Data;
import java.io.Serializable;

@Data
public class Casts implements Serializable {
    private String date;
    private String week;
    private String dayweather;
    private String nightweather;
    private String daytemp;
    private String nighttemp;
    private String daywind;
    private String nightwind;
    private String daypower;
    private String nightpower;
}

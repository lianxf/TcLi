package cn.likepeng.commons.core.utils.gmap;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class Weather implements Serializable {
    private String city;
    private String adcode;
    private String province;
    private String reporttime;
    private List<Casts> casts;
}

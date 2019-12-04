package cn.likepeng.commons.core.utils.gmap;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class City implements Serializable {
    private String citycode;
    private String adcode;
    private String level;
    private String center;
    private String name;
    private List<City> districts;
}

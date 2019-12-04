package commons.boot.objectmapper;

import cn.likepeng.commons.core.enumeration.TimePattern;
import lombok.Data;
import java.util.TimeZone;

@Data
public class EnableObjectMapper {
    private TimePattern datePattern = TimePattern.SECOND_PATTERN;
    private TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
    private Boolean numberToString = true;
    private Boolean isSerialNull = true;
}

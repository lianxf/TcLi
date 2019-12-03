package commons.boot.enable.serialization;

import commons.core.enumeration.TimePattern;
import lombok.Data;
import java.util.TimeZone;

@Data
public class ChangeSerialization {
    private TimePattern datePattern = TimePattern.SECOND_PATTERN;
    private TimeZone timeZone = TimeZone.getTimeZone("GMT+8");
    private Boolean  serialNull = true;
}

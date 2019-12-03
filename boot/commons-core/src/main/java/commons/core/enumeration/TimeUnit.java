package commons.core.enumeration;

import lombok.Getter;

@Getter
public enum TimeUnit {
    SECONDS(1),
    MINUTE(SECONDS.seconds*60),
    HOURS(MINUTE.seconds*60),
    DAY(HOURS.seconds*24),
    WEEK(DAY.seconds*7),
    MONTH(DAY.seconds*30);

    private long seconds;

    TimeUnit(long seconds) {
        this.seconds = seconds;
    }
}

package model;

import java.text.ParseException;
import java.time.Clock;
import java.time.LocalDateTime;

public class Time {
    private LocalDateTime time;

    public Time() {
        time = LocalDateTime.now();
    }

    public Time(LocalDateTime time) {
        this.time = time;
    }

    public static Time parse(String timeStr) throws ParseException {
        LocalDateTime currentTime = LocalDateTime.parse(timeStr);

        return new Time(currentTime);
    }
}

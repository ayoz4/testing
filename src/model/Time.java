package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Time {
    private String time;

    public Time() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        String dateToStr = format.format(date);

        time = dateToStr;
    }

    public Time(String time) {
        this.time = time;
    }

    public static Time parse(String timeStr) throws ParseException {
        Date date = new SimpleDateFormat("hh:mm").parse(timeStr);
        String newDate = new SimpleDateFormat("h:mm").format(date);

        System.out.println(date + " and " + newDate);

        return new Time(newDate);
    }

    @Override
    public String toString() {
        return time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Time time1 = (Time) o;
        return Objects.equals(time, time1.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time);
    }
}

package utils;

import java.time.LocalDateTime;
import java.time.LocalTime;


/**
 * Created by annadowling on 10/10/2016.
 */

public class DateTimeParser {

    public LocalTime parseDurationFromString(String duration) {
        return LocalTime.parse(duration);
    }

    public LocalDateTime parseStringToDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime);
    }
}

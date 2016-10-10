package utils;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Created by annadowling on 10/10/2016.
 */

public class DateTimeParser {

    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");


    public LocalTime parseDurationFromString(String duration) {
            return LocalTime.parse(duration);
    }

    public LocalDateTime parseStringToDateTime(String dateTime) {
        return LocalDateTime.parse(dateTime, dateFormat);
    }
}

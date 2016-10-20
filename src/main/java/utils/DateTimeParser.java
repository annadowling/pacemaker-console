package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.LocalTime;


/**
 * Created by annadowling on 10/10/2016.
 */

public class DateTimeParser {

    public String parseDurationFromString(String durationInput)
    {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
        LocalTime localTime = fmt.parseLocalTime(durationInput);
        return localTime.toString();
    }

    public String parseStringToDateTime(String dateTime)
    {
        String parsedDateTime = dateTime.replace("T", " ");
        DateTime startime = DateTime.parse(parsedDateTime, DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss"));
        return startime.toString();
    }
}

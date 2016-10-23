package utils;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.LocalTime;


/**
 * Created by annadowling on 10/10/2016.
 * DateTimeParser Class contains methods for handle datetime and duration parsing.
 */

public class DateTimeParser {

    /**
     * @param String durationInput
     * @return String
     * Method takes a String representing duration.
     * DateTimeFormatter sets the format type to parse "HH:mm:ss"
     * This format is then imposed on the parseLocalTime method of LocalTime, which we pass the durationInput.
     * The String format of localTime is then returned.
     */
    public String parseDurationFromString(String durationInput)
    {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("HH:mm:ss");
        LocalTime localTime = fmt.parseLocalTime(durationInput);
        return localTime.toString();
    }

    /**
     * @param String dateTime
     * @return String
     * Method takes a String representing dateTime.
     * We replace the handled format which contains T to differentiate the time with a space.
     * This format is then imposed on the parse method of DateTime, which we pass the parsedDateTime and format it as "yyyy-MM-dd hh:mm:ss".
     * The String format of startTime is then returned.
     */
    public String parseStringToDateTime(String dateTime)
    {
        String parsedDateTime = dateTime.replace("T", " ");
        DateTime startTime = DateTime.parse(parsedDateTime, DateTimeFormat.forPattern("yyyy-MM-dd hh:mm:ss"));
        return startTime.toString();
    }
}

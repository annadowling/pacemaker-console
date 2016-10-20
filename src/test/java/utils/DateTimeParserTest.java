package utils;
/**
 * Created by annadowling on 20/10/2016.
 */

import org.junit.jupiter.api.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(JUnitPlatform.class)
public class DateTimeParserTest {

    public static DateTimeParser parser;

    @BeforeAll
    static void initAll() {
        parser = new DateTimeParser();
    }

    @Test
    @DisplayName("Test Successful Duration Parsing from String")
    void testSuccessParsingDurationFromString() {
        String parsedDuration = parser.parseDurationFromString("12:00:00");
        assertEquals(parsedDuration, "12:00:00.000");
        assertNotEquals(parsedDuration, "12:00:00");
    }

    @Test
    @DisplayName("Test Successful Date Time Parsing from String")
    void testSuccessParsingDateTimeFromString() {
        String parsedDateTime = parser.parseStringToDateTime("2016-08-10 12:55:05");
        assertEquals(parsedDateTime, "2016-08-10T00:55:05.000+01:00");
        assertNotEquals(parsedDateTime, "2016-08-10 12:55:05");
    }

    @Test
    @DisplayName("Test IllegalArgumentException is thrown for incorrect duration format")
    void incorrectDurationFormatExceptionTesting() {
        Throwable exception = expectThrows(IllegalArgumentException.class, () -> {
            parser.parseDurationFromString("1214353464");
            throw new IllegalArgumentException("Invalid format: \"1214353464\" is malformed at \"14353464\"");
        });
        assertEquals("Invalid format: \"1214353464\" is malformed at \"14353464\"", exception.getMessage());
    }

    @Test
    @DisplayName("Test IllegalArgumentException is thrown for incorrect date time format")
    void incorrectDateTimeFormatExceptionTesting() {
        Throwable exception = expectThrows(IllegalArgumentException.class, () -> {
            parser.parseStringToDateTime("12-2016-08");
            throw new IllegalArgumentException("Invalid format: \"12-2016-08\" is malformed at \"16-08\"");
        });
        assertEquals("Invalid format: \"12-2016-08\" is malformed at \"16-08\"", exception.getMessage());
    }

    @AfterAll
    static void tearDownAll() {
        parser = null;
    }

}

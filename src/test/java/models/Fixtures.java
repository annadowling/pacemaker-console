package models;

import utils.DateTimeParser;

/**
 * Created by annadowling on 18/10/2016.
 * Helper class for abstracting the set up of Users, Activities and Locations for use with tests.
 */

public class Fixtures {

    public static DateTimeParser parser = new DateTimeParser();

    public static User[] users =
            {
                    new User ("marge", "simpson", "marge@simpson.com",  "secret"),
                    new User ("lisa",  "simpson", "lisa@simpson.com",   "secret"),
                    new User ("bart",  "simpson", "bart@simpson.com",   "secret"),
                    new User ("maggie","simpson", "maggie@simpson.com", "secret")
            };

    public static Activity[] activities =
            {
                    new Activity ("walk",  "fridge", 0.001, parser.parseStringToDateTime("2016-08-10 12:55:05"), parser.parseDurationFromString("12:00:00")),
                    new Activity ("walk",  "bar",    1.0, parser.parseStringToDateTime("2016-09-10 12:55:05"), parser.parseDurationFromString("02:00:00")),
                    new Activity ("run",   "work",   2.2, parser.parseStringToDateTime("2016-10-10 12:55:05"), parser.parseDurationFromString("02:00:00")),
                    new Activity ("walk",  "shop",   2.5, parser.parseStringToDateTime("2016-09-10 01:55:05"), parser.parseDurationFromString("00:30:00")),
                    new Activity ("cycle", "school", 4.5, parser.parseStringToDateTime("2016-09-10 02:55:05"), parser.parseDurationFromString("04:00:00"))
            };

    public static Location[] locations =
            {
                    new Location(23.3f, 33.3f),
                    new Location(34.4f, 22.2f),
                    new Location(25.3f, 34.3f),
                    new Location(44.4f, 23.3f)
            };
}

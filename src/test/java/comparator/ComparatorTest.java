package comparator;

import org.junit.Test;
import java.util.Arrays;
import static models.Fixtures.*;

/**
 * Created by annadowling on 19/10/2016.
 * Test Class for Comparator class and all of its associated behaviour.
 */
public class ComparatorTest {

    ComparatorLibrary comparator = new ComparatorLibrary();

    /**
     * Test for the expected sort order of activities sorted by starttime.
     */
    @Test
    public void testSortActivityByStartTime() {
        comparator.sortActivityByStartTime(Arrays.asList(activities));
        System.out.println(activities[4].starttime);
        assert activities[0].starttime.equals("2016-08-10T00:55:05.000+01:00");
        assert activities[1].starttime.equals("2016-09-10T00:55:05.000+01:00");
        assert activities[2].starttime.equals("2016-09-10T01:55:05.000+01:00");
        assert activities[3].starttime.equals("2016-09-10T02:55:05.000+01:00");
        assert activities[4].starttime.equals("2016-10-10T00:55:05.000+01:00");

    }

    /**
     * Test for the expected sort order of activities sorted by duration.
     */
    @Test
    public void testSortActivityByDuration() {
        comparator.sortActivityByDuration(Arrays.asList(activities));

        assert activities[0].duration.equals(parser.parseDurationFromString("00:30:00"));
        assert activities[1].duration.equals(parser.parseDurationFromString("02:00:00"));
        assert activities[2].duration.equals(parser.parseDurationFromString("02:00:00"));
        assert activities[3].duration.equals(parser.parseDurationFromString("04:00:00"));
        assert activities[4].duration.equals(parser.parseDurationFromString("12:00:00"));
    }

    /**
     * Test for the expected sort order of activities sorted by type.
     */
    @Test
    public void testSortActivityByType() {
        comparator.sortActivityByType(Arrays.asList(activities));

        assert activities[0].type.equals("cycle");
        assert activities[1].type.equals("run");
        assert activities[2].type.equals("walk");
        assert activities[3].type.equals("walk");
        assert activities[4].type.equals("walk");
    }

    /**
     * Test for the expected sort order of activities sorted by location.
     */
    @Test
    public void testSortActivityByLocation() {
        comparator.sortActivityByLocation(Arrays.asList(activities));

        assert activities[0].location.equals("bar");
        assert activities[1].location.equals("fridge");
        assert activities[2].location.equals("school");
        assert activities[3].location.equals("shop");
        assert activities[4].location.equals("work");
    }

    /**
     * Test for the expected sort order of activities sorted by distance.
     */
    @Test
    public void testSortActivityByDistance() {
        comparator.sortActivityByDistance(Arrays.asList(activities));

        assert activities[0].distance == 0.001;
        assert activities[1].distance == 1.0;
        assert activities[2].distance == 2.2;
        assert activities[3].distance == 2.5;
        assert activities[4].distance == 4.5;
    }
}

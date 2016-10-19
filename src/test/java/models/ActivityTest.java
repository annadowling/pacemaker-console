package models;

import org.junit.Test;
import utils.DateTimeParser;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static models.Fixtures.activities;


/**
 * Created by annadowling on 19/10/2016.
 */

public class ActivityTest {

    DateTimeParser parser = new DateTimeParser();

    Activity test = new Activity("walk", "fridge", 0.001, parser.parseStringToDateTime("2016-08-10T12:55:05"), parser.parseDurationFromString("12:00:00"));


    @Test
    public void testCreate() {
        assertEquals("walk", test.type);
        assertEquals("fridge", test.location);
        assertEquals(0.0001, 0.001, test.distance);
        assertEquals("2016-08-10T12:55:05", test.starttime.toString());
        assertEquals("12:00", test.duration.toString());
    }

    @Test
    public void testIds() {
        Set<Long> ids = new HashSet<>();
        for (Activity activity : activities) {
            ids.add(activity.id);
        }
        assertEquals(activities.length, ids.size());
    }

    @Test
    public void testToString() {
        assertEquals("Activity{" + test.id + ", walk, fridge, 0.001, 2016-08-10T12:55:05, 12:00, []}", test.toString());

    }
}

package models;

import org.junit.Test;
import utils.DateTimeParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
        assertEquals("2016-08-10T00:55:05.000+01:00", test.starttime.toString());
        assertEquals("12:00:00.000", test.duration.toString());
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
        assertEquals("Activity{" + test.id + ", walk, fridge, 0.001, 2016-08-10T00:55:05.000+01:00, 12:00:00.000, []}", test.toString());

    }

    @Test
    public void testGetId(){
        assert test.getId().equals(test.id);
    }

    @Test
    public void testGetType(){
        assert test.getType().equals(test.type);
    }

    @Test
    public void testGetDistance(){
        assert test.getDistance() == test.distance;
    }

    @Test
    public void testGetLocation(){
        assert test.getLocation().equals(test.location);
    }

    @Test
    public void testGetDuration(){
        assert test.getDuration() == test.duration;
    }

    @Test
    public void testGetStartTime(){
        assert test.getStarttime() == test.starttime;
    }

    @Test
    public void testGetRoutes(){
        List<Location> locationsList = new ArrayList<Location>();
        locationsList.add(new Location(44.3f, 88.3f));
        test.setRoute(locationsList);

        assertNotNull(test.getRoute());
        assert test.getRoute().size() == 1;
        assert test.getRoute().equals(locationsList);
    }



}

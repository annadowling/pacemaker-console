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
 * Test Class for Activity model and all of its associated behaviour.
 */

public class ActivityTest {

    DateTimeParser parser = new DateTimeParser();

    Activity test = new Activity("walk", "fridge", 0.001, parser.parseStringToDateTime("2016-08-10T12:55:05"), parser.parseDurationFromString("12:00:00"));


    /**
     * Test the creation of activity objects with all parameters.
     */
    @Test
    public void testCreate() {
        assertEquals("walk", test.type);
        assertEquals("fridge", test.location);
        assertEquals(0.0001, 0.001, test.distance);
        assertEquals("2016-08-10T00:55:05.000+01:00", test.starttime.toString());
        assertEquals("12:00:00.000", test.duration.toString());
    }

    /**
     * Test the uniqueness of id's between activity objects.
     */
    @Test
    public void testIds() {
        Set<Long> ids = new HashSet<>();
        for (Activity activity : activities) {
            ids.add(activity.id);
        }
        assertEquals(activities.length, ids.size());
    }

    /**
     * Test the expected output of the toString method of the Activity class.
     */
    @Test
    public void testToString() {
        assertEquals("Activity{" + test.id + ", walk, fridge, 0.001, 2016-08-10T00:55:05.000+01:00, 12:00:00.000, []}", test.toString());

    }

    /**
     * Test the expected output of the getter method for id of Activity.
     */
    @Test
    public void testGetId(){
        assert test.getId().equals(test.id);
    }

    /**
     * Test the expected output of the getter method for type of Activity.
     */
    @Test
    public void testGetType(){
        assert test.getType().equals(test.type);
    }

    /**
     * Test the expected output of the getter method for distance of Activity.
     */
    @Test
    public void testGetDistance(){
        assert test.getDistance() == test.distance;
    }

    /**
     * Test the expected output of the getter method for location of Activity.
     */
    @Test
    public void testGetLocation(){
        assert test.getLocation().equals(test.location);
    }

    /**
     * Test the expected output of the getter method for duration of Activity.
     */
    @Test
    public void testGetDuration(){
        assert test.getDuration() == test.duration;
    }

    /**
     * Test the expected output of the getter method for starttime of Activity.
     */
    @Test
    public void testGetStartTime(){
        assert test.getStarttime() == test.starttime;
    }

    /**
     * Test the expected output of the getter method for getting location objects(routes) associated with the Activity.
     */
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

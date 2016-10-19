package controllers;

import models.Activity;
import models.Location;
import models.User;
import org.junit.After;
import org.junit.Before;
import controllers.PaceMakerAPI;
import org.junit.Test;

import static models.Fixtures.users;
import static models.Fixtures.activities;
import static models.Fixtures.locations;
import static org.junit.Assert.*;

/**
 * Created by annadowling on 19/10/2016.
 */

public class PaceMakerAPITest {

    private PaceMakerAPI pacemaker;

    @Before
    public void setup()
    {
        pacemaker = new PaceMakerAPI(null);
        for (User user : users)
        {
            pacemaker.createUser(user.firstname, user.lastname, user.email, user.password);
        }
    }

    @After
    public void tearDown()
    {
        pacemaker = null;
    }

    @Test
    public void testUser()
    {
        assertEquals (users.length, pacemaker.getUsers().size());
        pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
        assertEquals (users.length+1, pacemaker.getUsers().size());
        assertEquals (users[0], pacemaker.getUserByEmail(users[0].email));
    }

    @Test
    public void testUsers()
    {
        assertEquals (users.length, pacemaker.getUsers().size());
        for (User user: users)
        {
            User eachUser = pacemaker.getUserByEmail(user.email);
            assertEquals (user, eachUser);
            assertNotSame(user, eachUser);
        }
    }

    @Test
    public void testDeleteUsers()
    {
        assertEquals (users.length, pacemaker.getUsers().size());
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        pacemaker.deleteUser(marge.id);
        assertEquals (users.length-1, pacemaker.getUsers().size());
    }

    @Test
    public void testAddActivity()
    {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Activity activity = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration);
        Activity returnedActivity = pacemaker.getActivity(activity.id);
        assertEquals(activities[0],  returnedActivity);
        assertNotSame(activities[0], returnedActivity);
    }

    @Test
    public void testAddActivityWithSingleLocation()
    {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Long activityId = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration).id;

        pacemaker.addLocation(activityId, locations[0].latitude, locations[0].longitude);

        Activity activity = pacemaker.getActivity(activityId);
        assertEquals (1, activity.route.size());
        assertEquals(0.0001, locations[0].latitude,  activity.route.get(0).latitude);
        assertEquals(0.0001, locations[0].longitude, activity.route.get(0).longitude);
    }

    @Test
    public void testAddActivityWithMultipleLocation()
    {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Long activityId = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration).id;

        for (Location location : locations)
        {
            pacemaker.addLocation(activityId, location.latitude, location.longitude);
        }

        Activity activity = pacemaker.getActivity(activityId);
        assertEquals (locations.length, activity.route.size());
        int i = 0;
        for (Location location : activity.route)
        {
            assertEquals(location, locations[i]);
            i++;
        }
    }
}

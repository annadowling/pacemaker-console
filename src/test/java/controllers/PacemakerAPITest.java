package controllers;

import models.Activity;
import models.Location;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.mail.internet.AddressException;

import static models.Fixtures.users;
import static models.Fixtures.activities;
import static models.Fixtures.locations;
import static org.junit.Assert.*;

/**
 * Created by annadowling on 19/10/2016.
 * Test Class for PaceMakerAPI class and all of its associated behaviour.
 */

public class PaceMakerAPITest {

    private PaceMakerAPI pacemaker;

    /**
     * Sets up user creation using PaceMakerAPI.createUser and the fixtures helper class
     */
    @Before
    public void setup() {
        pacemaker = new PaceMakerAPI(null);
        for (User user : users) {
            pacemaker.createUser(user.firstname, user.lastname, user.email, user.password);
        }
    }

    /**
     * Test user creation for expected parameter results.
     */
    @Test
    public void testUser() {
        assertEquals(users.length, pacemaker.getUsers().size());
        pacemaker.createUser("homer", "simpson", "homer@simpson.com", "secret");
        assertEquals(users.length + 1, pacemaker.getUsers().size());
        assertEquals(users[0], pacemaker.getUserByEmail(users[0].email));
    }

    /**
     * Test retrieval of expected users with method getUserByEmail.
     */
    @Test
    public void testUsers() {
        assertEquals(users.length, pacemaker.getUsers().size());
        for (User user : users) {
            User eachUser = pacemaker.getUserByEmail(user.email);
            assertEquals(user, eachUser);
            assertNotSame(user, eachUser);
        }
    }

    /**
     * Test deletion of users with method deleteUser which takes a user id as @param
     */
    @Test
    public void testDeleteUserById() {
        assertEquals(users.length, pacemaker.getUsers().size());
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        pacemaker.deleteUser(marge.id);
        assertEquals(users.length - 1, pacemaker.getUsers().size());
    }

    /**
     * Test the addition of activities using addActivity and their retrieval by id using getActivity
     */
    @Test
    public void testAddActivity() {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Activity activity = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration);
        Activity returnedActivity = pacemaker.getActivity(activity.id);
        assertEquals(activities[0], returnedActivity);
        assertNotSame(activities[0], returnedActivity);
    }

    /**
     * Test deletion of acitivities with method deleteActivity which takes an activity id as @param
     */
    @Test
    public void testDeleteActivityById() {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Activity activity = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration);
        pacemaker.deleteActivity(activity.getId());

        assertNull(pacemaker.getActivity(activity.getId()));
    }

    /**
     * Test adding a single location to an activity using addLocation method.
     */
    @Test
    public void testAddActivityWithSingleLocation() {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Long activityId = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration).id;

        pacemaker.addLocation(activityId, locations[0].latitude, locations[0].longitude);

        Activity activity = pacemaker.getActivity(activityId);
        assertEquals(1, activity.route.size());
        assertEquals(0.0001, locations[0].latitude, activity.route.get(0).latitude);
        assertEquals(0.0001, locations[0].longitude, activity.route.get(0).longitude);
    }

    /**
     * Test adding multiple locations to an activity using addLocation method.
     */
    @Test
    public void testAddActivityWithMultipleLocation() {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Long activityId = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration).id;

        for (Location location : locations) {
            pacemaker.addLocation(activityId, location.latitude, location.longitude);
        }

        Activity activity = pacemaker.getActivity(activityId);
        assertEquals(locations.length, activity.route.size());
        int i = 0;
        for (Location location : activity.route) {
            assertEquals(location, locations[i]);
            i++;
        }
    }

    /**
     * Test command to list activity by id, getActivity.
     */
    @Test
    public void testListActivityById() {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Long activityId = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration).id;
        Activity activity = pacemaker.getActivity(activityId);

        assertEquals(activity, activities[0]);
    }

    /**
     * Test command to list all acitivities for a user using listActivities method.
     */
    @Test
    public void testListAllActivities() {
        User marge = pacemaker.getUserByEmail("marge@simpson.com");
        Activity activity1 = pacemaker.addActivity(marge.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration);
        Activity activity2 = pacemaker.addActivity(marge.id, activities[1].type, activities[1].location, activities[1].distance, activities[1].starttime, activities[1].duration);

        assertNotNull(pacemaker.listActivities());
        assert pacemaker.listActivities().size() == 2;
        assertTrue(pacemaker.listActivities().contains(activity1));
        assertTrue(pacemaker.listActivities().contains(activity2));
    }

    /**
     * Test the checkEmailValidation method to ensure valid email input.
     */
    @Test
    public void testUserEmailValidation() throws Exception {
        try {
            User marge = pacemaker.getUserByEmail("marge@simpson.com");
            assertTrue(pacemaker.checkEmailValidation(marge.getEmail()));

            String invalidEmail = "dhsjdhjashdjs";
            assertFalse(pacemaker.checkEmailValidation(invalidEmail));
        } catch (AddressException e) {
            assert e.getMessage().equals("Email validation has failed, please enter a valid format for: dhsjdhjashdjs");
        }

    }

    /**
     * After the tests are run tearDown the instance of PaceMakerAPI by setting it to null.
     */
    @After
    public void tearDown() {
        pacemaker = null;
    }
}

package controllers;

import models.Activity;
import models.Location;
import models.User;
import org.junit.Test;
import utils.*;

import java.io.File;

import static models.Fixtures.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by annadowling on 19/10/2016.
 * Test Class for Serializer classes and all of their associated behaviour.
 */

public class PersistenceTest {
    PaceMakerAPI pacemaker;

    /**
     * @param pacemaker
     * Sets up users, activities and locations which are stored in the PaceMakerAPI instance.
     */
    void populate(PaceMakerAPI pacemaker) {
        for (User user : users) {
            pacemaker.createUser(user.firstname, user.lastname, user.email, user.password);
        }
        User user1 = pacemaker.getUserByEmail(users[0].email);
        Activity activity = pacemaker.addActivity(user1.id, activities[0].type, activities[0].location, activities[0].distance, activities[0].starttime, activities[0].duration);
        pacemaker.addActivity(user1.id, activities[1].type, activities[1].location, activities[1].distance, activities[1].starttime, activities[1].duration);
        User user2 = pacemaker.getUserByEmail(users[1].email);
        pacemaker.addActivity(user2.id, activities[2].type, activities[2].location, activities[2].distance, activities[2].starttime, activities[2].duration);
        pacemaker.addActivity(user2.id, activities[3].type, activities[3].location, activities[3].distance, activities[3].starttime, activities[3].duration);


        for (Location location : locations) {
            pacemaker.addLocation(activity.id, location.latitude, location.longitude);
        }
    }

    /**
     * @param fileName
     * Helper method for tests to delete files is the already exist.
     */
    void deleteFile(String fileName) {
        File datastore = new File(fileName);
        if (datastore.exists()) {
            datastore.delete();
        }
    }

    /**
     * Tests that the population and getter methods associated with PaceMakerAPI are working correctly.
     */
    @Test
    public void testPopulate() {
        pacemaker = new PaceMakerAPI(null);

        assertEquals(0, pacemaker.getUsers().size());
        populate(pacemaker);

        assertEquals(users.length, pacemaker.getUsers().size());
        assertEquals(2, pacemaker.getUserByEmail(users[0].email).activities.size());
        assertEquals(2, pacemaker.getUserByEmail(users[1].email).activities.size());
        Long activityID = pacemaker.getUserByEmail(users[0].email).activities.keySet().iterator().next();
        assertEquals(locations.length, pacemaker.getActivity(activityID).route.size());
    }

    /**
     * Tests the XMLSerializer class for storing data in the .xml format
     * Tests the store() and load() methods of PaceMakerAPI
     */
    @Test
    public void testXMLSerializer() throws Exception {
        String datastoreFile = "testdatastore.xml";
        deleteFile(datastoreFile);

        Serializer serializer = new XMLSerializer(new File(datastoreFile));

        pacemaker = new PaceMakerAPI(serializer);
        populate(pacemaker);
        pacemaker.store();

        PaceMakerAPI pacemaker2 = new PaceMakerAPI(serializer);
        pacemaker2.load();

        assertEquals(pacemaker.getUsers().size(), pacemaker2.getUsers().size());
        for (User user : pacemaker.getUsers()) {
            assertTrue(pacemaker2.getUsers().contains(user));
        }
        deleteFile("testdatastore.xml");
    }

    /**
     * Tests the BinarySerializer class for storing data in the .txt format
     * Tests the store() and load() methods of PaceMakerAPI
     */
    @Test
    public void testBinarySerializer() throws Exception {
        String datastoreFile = "testdatastore.txt";
        deleteFile(datastoreFile);

        Serializer serializer = new BinarySerializer(new File(datastoreFile));

        pacemaker = new PaceMakerAPI(serializer);
        populate(pacemaker);
        pacemaker.store();

        PaceMakerAPI pacemaker2 = new PaceMakerAPI(serializer);
        pacemaker2.load();

        assertEquals(pacemaker.getUsers().size(), pacemaker2.getUsers().size());
        for (User user : pacemaker.getUsers()) {
            assertTrue(pacemaker2.getUsers().contains(user));
        }
        deleteFile("testdatastore.txt");
    }

    /**
     * Tests the JSONSerializer class for storing data in the .JSON format
     * Tests the store() and load() methods of PaceMakerAPI
     */
    @Test
    public void testJSONSerializer() throws Exception {
        String datastoreFile = "testdatastore.JSON";
        deleteFile(datastoreFile);

        Serializer serializer = new JSONSerializer(new File(datastoreFile));

        pacemaker = new PaceMakerAPI(serializer);
        populate(pacemaker);
        pacemaker.store();

        PaceMakerAPI pacemaker2 = new PaceMakerAPI(serializer);
        pacemaker2.load();

        assertEquals(pacemaker.getUsers().size(), pacemaker2.getUsers().size());
        for (User user : pacemaker.getUsers()) {
            assertTrue(pacemaker2.getUsers().contains(user));
        }
        deleteFile("testdatastore.JSON");
    }

    /**
     * Tests the YAMLSerializer class for storing data in the .yml format
     * Tests the store() and load() methods of PaceMakerAPI
     */
    @Test
    public void testYAMLSerializer() throws Exception {
        String datastoreFile = "testdatastore.yml";
        deleteFile(datastoreFile);

        Serializer serializer = new YAMLSerializer(new File(datastoreFile));

        pacemaker = new PaceMakerAPI(serializer);
        populate(pacemaker);
        pacemaker.store();

        PaceMakerAPI pacemaker2 = new PaceMakerAPI(serializer);
        pacemaker2.load();

        assertEquals(pacemaker.getUsers().size(), pacemaker2.getUsers().size());
        deleteFile("testdatastore.yml");
    }


}

package controllers;

import java.io.*;
import java.util.*;

import utils.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Activity;
import models.Location;
import models.User;

/**
 * Created by annadowling on 23/09/2016.
 * PaceMakerAPI Class contains methods which are used in the adding and retrieving of data used by the command set.
 */
public class PaceMakerAPI {
    private Map<Long, User> userIndex = new HashMap<Long, User>();
    private Map<String, User> emailIndex = new HashMap<String, User>();
    private Map<Long, Activity> activityIndex = new HashMap<Long, Activity>();
    private List<Location> locationIndex = new ArrayList<Location>();
    private Serializer serializer;

    /**
     * @param Serializer
     * Overloaded constructor for PaceMakerAPI
     */
    public PaceMakerAPI(Serializer serializer) {
        this.serializer = serializer;
    }

    /**
     * Reads data from the serializer class
     * Uses the pop method to remove these objects from the stack and add them to associated index Map or list.
     */
    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        serializer.read();
        activityIndex = (Map<Long, Activity>) serializer.pop();
        emailIndex = (Map<String, User>) serializer.pop();
        userIndex = (Map<Long, User>) serializer.pop();
    }

    /**
     * Writes data from the serializer class
     * Uses the push method to add these objects to the stack from the associated index Map or list.
     */
    @SuppressWarnings("unchecked")
    public void store() throws Exception {
        serializer.push(userIndex);
        serializer.push(emailIndex);
        serializer.push(activityIndex);
        serializer.write();
    }

    /**
     * @return Collection<User>
     * Returns a collection of User objects from the userIndex Map.
     */
    public Collection<User> getUsers() {
        return userIndex.values();
    }

    /**
     * Deletes all users by clearing all objects from the userIndex and emailIndex maps.
     */
    public void deleteUsers() {
        userIndex.clear();
        emailIndex.clear();
    }

    /**
     * @param String firstName, String lastName, String email, String password
     * @return User
     * Creates a new user object using the specified paramaters.
     * adds this user to the Map userIndex
     * adds this users email and object reference to the emailIndex.
     */
    public User createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, password);
        userIndex.put(user.id, user);
        emailIndex.put(email, user);
        return user;
    }

    /**
     * @param String email
     * @return User
     * Returns a user object from the emailIndex by using an email string as the key search.
     */
    public User getUserByEmail(String email) {
        return emailIndex.get(email);
    }

    /**
     * @param Long id
     * @return User
     * Returns a user object from the userIndex by using an id value.
     */
    public User getUser(Long id) {
        return userIndex.get(id);
    }


    /**
     * @param Long id
     * @return User
     * Returns a user object from the userIndex by using an id value.
     */
    public User listUser(Long id) {
        return userIndex.get(id);
    }

    /**
     * @return List<User>
     * Returns a list of users from the userIndex map by iterating over the entrySet()
     */
    public List<User> listUsers() {
        List<User> usersInMap = new ArrayList<User>();
        for (Map.Entry<Long, User> entry : userIndex.entrySet()) {
            User mapEntry = entry.getValue();
            usersInMap.add(mapEntry);
        }
        return usersInMap;
    }

    /**
     * @return List<Activity>
     * Returns a list of activities from the activityIndex map by iterating over the entrySet()
     */
    public List<Activity> listActivities() {
        List<Activity> activitiesInMap = new ArrayList<Activity>();
        for (Map.Entry<Long, Activity> entry : activityIndex.entrySet()) {
            Activity mapEntry = entry.getValue();
            activitiesInMap.add(mapEntry);
        }
        return activitiesInMap;
    }

    /**
     * @param Long id
     * Finds the user by the specified id.
     * Checks that user isPresent before removing them from the userIndex map and the emailIndex map.
     */
    public void deleteUser(Long id) {
        Optional<User> user = Optional.ofNullable(userIndex.get(id));
        if (user.isPresent()) {
            userIndex.remove(id);
            emailIndex.remove(user.get().email);
        }
    }

    /**
     * @param Long id
     * @return Activity
     * Returns an activity object from the activityIndex by using an id value.
     */
    public Activity getActivity(Long id) {
        return activityIndex.get(id);
    }

    /**
     * @param Long id
     * Finds the activity by the specified id.
     * Checks that activity isPresent before removing them from the activityIndex map.
     */
    public void deleteActivity(Long id) {
        Optional<Activity> activity = Optional.ofNullable(activityIndex.get(id));
        if (activity.isPresent()) {
            activityIndex.remove(id);
        }

    }

    /**
     * @param Long userId, String type, String location, double distance, String starttime, String duration
     * @return Activity
     * Creates a new activity object using the specified paramaters.
     * Checks if the user is present which we want to add the activity to.
     * adds this acitivty to the users activities.
     * adds this activity to the Map activityIndex
     */
    public Activity addActivity(Long userId, String type, String location, double distance, String starttime, String duration) {
        Activity activity = null;
        Optional<User> user = Optional.ofNullable(userIndex.get(userId));
        if (user.isPresent()) {
            activity = new Activity(type, location, distance, starttime, duration);
            user.get().activities.put(activity.id, activity);
            activityIndex.put(activity.id, activity);
        }
        return activity;
    }

    /**
     * @param Long activityId, float latitude, float longitude
     * Creates a new location object using the specified paramaters.
     * Checks if the activity is present which we want to add the location to.
     * adds this location to the activity instance (route)
     */
    public void addLocation(Long activityId, float latitude, float longitude) {
        Optional<Activity> activity = Optional.ofNullable(activityIndex.get(activityId));
        if (activity.isPresent()) {
            activity.get().route.add(new Location(latitude, longitude));
        }
    }

    /**
     * @param File file
     * Loads a file contents into memory using Xstream
     * Creates an createObjectInputStream of type FileReader
     * Reads the indexMaps into memory.
     * Finally close the stream.
     */
    @SuppressWarnings("unchecked")
    public void load(File file) throws Exception {
        ObjectInputStream is = null;
        try {
            XStream xstream = new XStream(new DomDriver());
            is = xstream.createObjectInputStream(new FileReader(file));
            userIndex = (Map<Long, User>) is.readObject();
            emailIndex = (Map<String, User>) is.readObject();
            activityIndex = (Map<Long, Activity>) is.readObject();
        } catch (EOFException e) {
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    /**
     * @param File file
     * Writes a file contents in memory using Xstream
     * Creates an createObjectOutputStream of type FileWriter
     * Writes the indexMaps to the file.
     * Finally close the stream.
     */
    public void store(File file) throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(userIndex);
        out.writeObject(emailIndex);
        out.writeObject(activityIndex);
        out.close();
    }

    /**
     * Sets the file type to .yml
     * Assigns the serializer to YAMLSerializer
     * Loads the readObjects.
     */
    @SuppressWarnings("unchecked")
    public void useYAMLFileFormat() throws Exception {
        File datastore = new File("datastore.yml");
        serializer = new YAMLSerializer(datastore);

        if (datastore.isFile()) {
            load();
        }
    }


    /**
     * Sets the file type to .JSON
     * Assigns the serializer to JSONSerializer
     * Loads the readObjects.
     */
    @SuppressWarnings("unchecked")
    public void useJSONFileFormat() throws Exception {
        File datastore = new File("datastore.JSON");
        serializer = new JSONSerializer(datastore);

        if (datastore.isFile()) {
            load();
        }
    }

    /**
     * Sets the file type to .txt
     * Assigns the serializer to BinarySerializer
     * Loads the readObjects.
     */
    @SuppressWarnings("unchecked")
    public void useBinaryFileFormat() throws Exception {
        File datastore = new File("datastore.txt");
        serializer = new BinarySerializer(datastore);

        if (datastore.isFile()) {
            load();
        }
    }

    /**
     * Sets the file type to .xml
     * Assigns the serializer to XMLSerializer
     * Loads the readObjects.
     */
    @SuppressWarnings("unchecked")
    public void useXMLFileFormat() throws Exception {
        File datastore = new File("datastore.xml");
        serializer = new XMLSerializer(datastore);

        if (datastore.isFile()) {
            load();
        }
    }
}
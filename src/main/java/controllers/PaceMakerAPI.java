package controllers;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import utils.Serializer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Activity;
import models.Location;
import models.User;

public class PaceMakerAPI {
    private Map<Long, User> userIndex = new HashMap<Long, User>();
    private Map<String, User> emailIndex = new HashMap<String, User>();
    private Map<Long, Activity> activityIndex = new HashMap<Long, Activity>();
    private List<Location> locationIndex = new ArrayList<Location>();
    private Serializer serializer;

    public PaceMakerAPI(Serializer serializer) {
        this.serializer = serializer;
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception {
        serializer.read();
        activityIndex = (Map<Long, Activity>) serializer.pop();
        emailIndex = (Map<String, User>) serializer.pop();
        userIndex = (Map<Long, User>) serializer.pop();
    }

    public void store() throws Exception {
        serializer.push(userIndex);
        serializer.push(emailIndex);
        serializer.push(activityIndex);
        serializer.write();
    }

    public Collection<User> getUsers() {
        return userIndex.values();
    }

    public void deleteUsers() {
        userIndex.clear();
        emailIndex.clear();
    }

    public User createUser(String firstName, String lastName, String email, String password) {
        User user = new User(firstName, lastName, email, password);
        userIndex.put(user.id, user);
        emailIndex.put(email, user);
        return user;
    }

    public User getUserByEmail(String email) {
        return emailIndex.get(email);
    }

    public User getUser(Long id) {
        return userIndex.get(id);
    }

    public User listUser(Long id) {
        return userIndex.get(id);
    }

    public List<User> listUsers() {
        List<User> usersInMap = new ArrayList<User>();
        for (Map.Entry<Long, User> entry : userIndex.entrySet()) {
            User mapEntry = entry.getValue();
            usersInMap.add(mapEntry);
        }
        return usersInMap;
    }

    public void deleteUser(Long id) {
        Optional<User> user = Optional.ofNullable(userIndex.get(id));
        if (user.isPresent()) {
            userIndex.remove(id);
            emailIndex.remove(user.get().email);
        }
    }

    public Activity getActivity(Long id) {
        return activityIndex.get(id);
    }

    public void deleteActivity(Long id) {
        Optional<Activity> activity = Optional.ofNullable(activityIndex.get(id));
        if (activity.isPresent()) {
            activityIndex.remove(id);
        }

    }

    public List<Location> getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(List<Location> locationIndex) {
        this.locationIndex = locationIndex;
    }

    public void addActivity(Long userId, String type, String location, double distance, LocalDateTime activityDate, Duration activityDuration) {
        Activity activity = new Activity(type, location, distance, activityDate, activityDuration);
        Optional<User> user = Optional.ofNullable(userIndex.get(userId));
        if (user.isPresent()) {
            user.get().activities.put(activity.id, activity);
            activityIndex.put(activity.id, activity);
        }
    }

    public void addLocation(Long activityId, float latitude, float longitude) {
        Optional<Activity> activity = Optional.ofNullable(activityIndex.get(activityId));
        if (activity.isPresent()) {
            activity.get().route.add(new Location(latitude, longitude));
        }
    }

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

    public void store(File file) throws Exception {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter(file));
        out.writeObject(userIndex);
        out.writeObject(emailIndex);
        out.writeObject(activityIndex);
        out.close();
    }
}
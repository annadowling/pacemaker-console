package controllers;

import java.util.*;

import models.Activity;
import models.Location;
import models.User;

public class PaceMakerAPI {
    private Map<Long, User> userIndex = new HashMap<>();
    private Map<String, User> emailIndex = new HashMap<>();
    private Map<Long, Activity> activityIndex = new HashMap<>();
    private List<Location> locationIndex = new ArrayList<>();

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

    public void deleteUser(Long id) {
        User user = userIndex.remove(id);
        emailIndex.remove(user.email);
    }

    public Activity getActivity(Long id) {
        return activityIndex.get(id);
    }

    public void deleteActivity(Long id){
        Activity activity = activityIndex.remove(id);
    }

    public List<Location> getLocationIndex() {
        return locationIndex;
    }

    public void setLocationIndex(List<Location> locationIndex) {
        this.locationIndex = locationIndex;
    }

    public Activity addActivity(Long userId, String type, String location, Double distance) {
        Activity activity = new Activity(type, location, distance);
        User userToAddActivityTo = getUser(userId);
        Map<Long, Activity> activitiesMap = new HashMap<>();
        if (userToAddActivityTo != null) {
            activityIndex.put(activity.id, activity);
            activitiesMap.put(userId, activity);
            userToAddActivityTo.setActivities(activitiesMap);
        }
        return activity;
    }

    public Location addLocation(Long activityId, Double latitude, Double longitude) {
        List<Location> locationsList = new ArrayList<>();
        Location location = new Location(latitude, longitude);
        locationIndex.add(location);
        locationsList.add(location);
        Activity activityToAddLocationTo = getActivity(activityId);
        if (activityToAddLocationTo != null) {
            activityToAddLocationTo.setRoute(locationsList);
        }
        return location;
    }
}
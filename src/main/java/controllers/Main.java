package controllers;

import java.io.*;
import java.time.format.DateTimeParseException;
import java.util.*;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import com.google.common.base.Optional;
import comparator.ComparatorLibrary;
import models.Activity;
import models.User;
import utils.*;

/**
 * Created by annadowling on 23/09/2016.
 * Main Class contains commands and run code for creating and running the pacemaker console.
 */
public class Main {
    private PaceMakerAPI paceApi;
    ASCIIFormatter asciiFormatter = new ASCIIFormatter();
    DateTimeParser dateTimeParser = new DateTimeParser();
    ComparatorLibrary comparator = new ComparatorLibrary();


    /**
     * @return Main
     * Main class constructor
     * Sets the default fileType and serializer
     * load the file into memory
     */
    public Main() throws Exception {
        File datastore = new File("datastore.xml");
        Serializer serializer = new XMLSerializer(datastore);

        paceApi = new PaceMakerAPI(serializer);
        if (datastore.isFile()) {
            paceApi.load();
        }
    }

    /**
     * main run method for pacemaker console application
     * instantiates main constructor and createsthe console shell
     */
    public static void main(String[] args) throws Exception {
        Main main = new Main();

        Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions or ?list to view all commands", main);
        shell.commandLoop();

        main.paceApi.store();
    }

    /**
     * Reads the serialised data from file.
     */
    @Command(description = "load all data")
    public void load() throws Exception {
        paceApi.load();
    }

    /**
     * Writes the serialised data to file.
     */
    @Command(description = "store all data")
    public void store() throws Exception {
        paceApi.store();
    }

    /**
     * @param String firstName, lastName, email, password
     * Creates a new user object.
     */
    @Command(description = "Create a new User")
    public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
                           @Param(name = "email") String email, @Param(name = "password") String password) {
        paceApi.createUser(firstName, lastName, email, password);
    }

    /**
     * @param String email
     * @return String userDetails
     * returns user details based on the email String passed to the console.
     * Formats the output using asciiFormatter.formatUsers.
     */
    @Command(description = "Get a Users details")
    public String getUser(@Param(name = "email") String email) {
        User user = paceApi.getUserByEmail(email);
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        return asciiFormatter.formatUsers(userList);
    }

    /**
     * @return String userDetails
     * returns all user details currently stored.
     * Formats the output using asciiFormatter.formatUsers.
     */
    @Command(description = "Get all users details")
    public String getUsers() {
        List<User> userList = new ArrayList<>(paceApi.getUsers());
        return asciiFormatter.formatUsers(userList);
    }

    /**
     * @param String email
     * Deletes a user entry based on the email address passed to the console.
     */
    @Command(description = "Delete a User")
    public void deleteUser(@Param(name = "email") String email) {
        Optional<User> user = Optional.fromNullable(paceApi.getUserByEmail(email));
        if (user.isPresent()) {
            paceApi.deleteUser(user.get().id);
        }
    }

    /**
     * @param Long id
     * Deletes a user entry based on the user id passed to the console.
     */
    @Command(description = "Delete a User by id")
    public void deleteUser(@Param(name = "id") Long id) {
        Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
        if (user.isPresent()) {
            paceApi.deleteUser(user.get().id);
        }
    }

    /**
     * @param Long user id, String type, location, distance, starttime, duration.
     * Creates a new Activity object.
     * Uses the dateTimeParser to format starttime and duration input.
     * Handles DateTimeParseException
     */
    @Command(description = "Add an activity")
    public void addActivity(@Param(name = "user-id") Long id, @Param(name = "type") String type,
                            @Param(name = "location") String location, @Param(name = "distance") double distance,
                            @Param(name = "start time") String starttime,
                            @Param(name = "duration(HH:mm:ss)") String duration) {
        Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
        if (user.isPresent()) {
            try {
                String parsedDuration = dateTimeParser.parseDurationFromString(duration);
                String parsedStartTime = dateTimeParser.parseStringToDateTime(starttime);
                paceApi.addActivity(id, type, location, distance, parsedStartTime, parsedDuration);
            } catch (DateTimeParseException exception) {
                System.out.println("Please enter the duration in the following format: hh:mm:ss and the starttime in the following format yyyy-MM-ddTHH:mm:ss");
            }
        }
    }

    /**
     * @param Long id
     * Deletes an activity entry based on the activity id passed to the console.
     */
    @Command(description = "Delete an Activity")
    public void deleteActivity(@Param(name = "id") Long id) {
        Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
        if (activity.isPresent()) {
            paceApi.deleteActivity(activity.get().id);
        }
    }

    /**
     * @param Long activityId, float latitude, float longitude
     * Adds a location to an activity entry.
     */
    @Command(description = "Add a Location")
    public void addLocation(@Param(name = "activity-id") Long activityId, @Param(name = "latitude") float latitude,
                            @Param(name = "longitude") float longitude) {
        Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(activityId));
        if (activity.isPresent()) {
            paceApi.addLocation(activity.get().id, latitude, longitude);
        }
    }

    /**
     * @param Long id
     * @return String user entry
     * returns a user entry based on the user id passed to the console.
     * Formats the output using asciiFormatter.formatUsers.
     */
    @Command(description = "List a User entry by id")
    public String listUser(@Param(name = "id") Long id) {
        User user = paceApi.listUser(id);
        List<User> userList = new ArrayList<User>();
        userList.add(user);
        return asciiFormatter.formatUsers(userList);
    }

    /**
     * @return String user entry
     * returns all user entries to the console.
     * Formats the output using asciiFormatter.formatUsers.
     */
    @Command(description = "List all User entries")
    public String listUsers() {
        List<User> users = paceApi.listUsers();
        return asciiFormatter.formatUsers(users);
    }

    /**
     * @param Long id
     * @return String activity entry
     * returns an activity entry based on the user id passed to the console.
     * check that the user exists using isPresent().
     * Formats the output using asciiFormatter.formatActivity.
     */
    @Command(description = "List an Activity by user id")
    public String listActivitiesByUserId(@Param(name = "id") Long userId) {
        Optional<User> user = Optional.fromNullable(paceApi.getUser(userId));
        Set<Long> activityIds = null;
        List<Activity> activityList = new ArrayList<Activity>();
        if (user.isPresent()) {
            activityIds = user.get().activities.keySet();
        }
        for (Long activityId : activityIds) {
            Activity activity = paceApi.getActivity(activityId);
            activityList.add(activity);
        }
        return asciiFormatter.formatActivity(activityList);
    }

    /**
     * @return String activity entry
     * returns all activity entries to the console.
     * Formats the output using asciiFormatter.formatActivity.
     */
    @Command(description = "List all Activities")
    public String listAllActivities() {
        List<Activity> activityList = paceApi.listActivities();
        return asciiFormatter.formatActivity(activityList);
    }

    /**
     * @param String sortType - choose from Activity starttime, duration, type, location, distance
     * @return String Activity entry
     * returns all activity entries to the console ordered by the sorttype passed to the console.
     * Formats the output using asciiFormatter.formatActivity.
     */
    @Command(description = "List all Activities by sort type(starttime, duration, type, location, distance)")
    public String listAllActivitieBySortType(@Param(name = "sortType") String sortType) {
        List<Activity> activityList = paceApi.listActivities();
        if(sortType.equals("starttime")){
            comparator.sortActivityByStartTime(activityList);
        }else if(sortType.equals("duration")){
            comparator.sortActivityByDuration(activityList);
        }else if(sortType.equals("type")){
            comparator.sortActivityByLocation(activityList);
        }else if(sortType.equals("location")){
            comparator.sortActivityByType(activityList);
        }else if(sortType.equals("distance")){
            comparator.sortActivityByDistance(activityList);
        }else{
            System.out.println("Please enter a valid sort type from the following options: starttime, duration, type, location, distance");
        }
        return asciiFormatter.formatActivity(activityList);
    }

    /**
     * @param String fileFormat
     * Changes the serializer and fileFormat output of PaceMakerAPI
     * Changes the output based on the fileFormat passed in to the console.
     * exception handling for incorrect fileFormat String.
     */
    @Command(description = "Change File Format")
    public void changeFileFormat(@Param(name = "fileFormat(xml, json, yaml, binary)") String fileFormat) throws Exception {
        if (fileFormat.equals("json")) {
            paceApi.useJSONFileFormat();
        } else if (fileFormat.equals("xml")) {
            paceApi.useXMLFileFormat();
        } else if (fileFormat.equals("yaml")) {
            paceApi.useYAMLFileFormat();
        } else if (fileFormat.equals("binary")) {
            paceApi.useBinaryFileFormat();
        } else {
            System.out.println("File format is unrecognised, please try again");
        }
    }
}
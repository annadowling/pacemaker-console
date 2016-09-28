package controllers;

import java.io.File;
import java.util.Collection;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import com.google.common.base.Optional;
import models.Activity;
import models.User;
import utils.XMLSerializer;
import utils.Serializer;

public class Main {
    private PaceMakerAPI paceApi;

    public Main() throws Exception {
        File datastore = new File("datastore.xml");
        Serializer serializer = new XMLSerializer(datastore);

        paceApi = new PaceMakerAPI(serializer);
        if (datastore.isFile()) {
            paceApi.load();
        }
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        Shell shell = ShellFactory.createConsoleShell("pm", "Welcome to pacemaker-console - ?help for instructions", main);
        shell.commandLoop();

        main.paceApi.store();
    }

    @Command(description = "Create a new User")
    public void createUser(@Param(name = "first name") String firstName, @Param(name = "last name") String lastName,
                           @Param(name = "email") String email, @Param(name = "password") String password) {
        paceApi.createUser(firstName, lastName, email, password);
    }

    @Command(description = "Get a Users details")
    public void getUser(@Param(name = "email") String email) {
        User user = paceApi.getUserByEmail(email);
        System.out.println(user);
    }

    @Command(description = "Get all users details")
    public void getUsers() {
        Collection<User> users = paceApi.getUsers();
        System.out.println(users);
    }

    @Command(description = "Delete a User")
    public void deleteUser(@Param(name = "email") String email) {
        Optional<User> user = Optional.fromNullable(paceApi.getUserByEmail(email));
        if (user.isPresent()) {
            paceApi.deleteUser(user.get().id);
        }
    }

    @Command(description = "Add an activity")
    public void addActivity(@Param(name = "user-id") Long id, @Param(name = "type") String type,
                            @Param(name = "location") String location, @Param(name = "distance") double distance) {
        Optional<User> user = Optional.fromNullable(paceApi.getUser(id));
        if (user.isPresent()) {
            paceApi.addActivity(id, type, location, distance);
        }
    }

    @Command(description = "Delete an Activity")
    public void deleteActivity(@Param(name = "id") Long id) {
        Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
        if (activity.isPresent()) {
            paceApi.deleteActivity(activity.get().id);
        }
    }

    @Command(description = "Add a Location")
    public void addLocation(@Param(name = "activity-id") Long activityId, @Param(name = "latitude") float latitude,
                            @Param(name = "longitude") float longitude) {
        Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(activityId));
        if (activity.isPresent()) {
            paceApi.addLocation(activity.get().id, latitude, longitude);
        }
    }
}
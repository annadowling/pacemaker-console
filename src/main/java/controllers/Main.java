package controllers;

import java.io.IOException;
import java.util.Collection;

import asg.cliche.Command;
import asg.cliche.Param;
import asg.cliche.Shell;
import asg.cliche.ShellFactory;
import com.google.common.base.Optional;
import models.Activity;
import models.User;

public class Main {
    PaceMakerAPI paceApi = new PaceMakerAPI();

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

    @Command(description = "Add an Activity")
    public void addActivity(@Param(name = "user-id") Long userId, @Param(name = "type") String type,
                            @Param(name = "location") String location, @Param(name = "distance") Double distance) {
        paceApi.addActivity(userId, type, location, distance);
    }

    @Command(description = "Delete an Activity")
    public void deleteActivity(@Param(name = "id") Long id) {
        Optional<Activity> activity = Optional.fromNullable(paceApi.getActivity(id));
        if (activity.isPresent()) {
            paceApi.deleteActivity(activity.get().id);
        }
    }

    @Command(description = "Add a Location")
    public void addLocation(@Param(name = "activity-id") Long activityId, @Param(name = "latitude") Double latitude,
                            @Param(name = "longitude") Double longitude) {
        paceApi.addLocation(activityId, latitude, longitude);
    }

    public static void main(String[] args) throws IOException {
        Shell shell = ShellFactory.createConsoleShell("pc", "Welcome to pacemaker-console - ?help for instructions", new Main());
        shell.commandLoop();
    }
}
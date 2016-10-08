package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.Map;

public class User {
    static Long   counter = 0l;
    public Long   id;
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public Map<Long, Activity> activities = new HashMap<Long, Activity>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password) {
        this.id        = counter++;
        this.firstname = firstName;
        this.lastname = lastName;
        this.email = email;
        this.password = password;
    }

    public static Long getCounter() {
        return counter;
    }

    public static void setCounter(Long counter) {
        User.counter = counter;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<Long, Activity> getActivities() {
        return activities;
    }

    public void setActivities(Map<Long, Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .addValue(id)
                .addValue(firstname)
                .addValue(lastname)
                .addValue(password)
                .addValue(email)
                .addValue(activities)
                .toString();
    }

    @Override
    public boolean equals(final Object obj)
    {
        if (obj instanceof User)
        {
            final User userInstance = (User) obj;
            return Objects.equal(firstname,   userInstance.firstname)
                    && Objects.equal(lastname,    userInstance.lastname)
                    && Objects.equal(email,       userInstance.email)
                    && Objects.equal(password,    userInstance.password)
                    && Objects.equal(activities,  userInstance.activities);
        }
        else
        {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.lastname, this.firstname, this.email, this.password, this.activities);
    }
}
package models;

import static com.google.common.base.MoreObjects.toStringHelper;

import com.google.common.base.Objects;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by annadowling on 23/09/2016.
 * Class for modelling the structure of User and all of its associated relationships.
 */
public class User implements Serializable{
    static Long   counter = 0l;
    public Long   id;
    public String firstname;
    public String lastname;
    public String email;
    public String password;
    public Map<Long, Activity> activities = new HashMap<Long, Activity>();

    /**
     * Empty constructor for User model.
     */
    public User() {
    }

    /**
     * Overloaded constructor for User model.
     */
    public User(String firstname, String lastname, String email, String password) {
        this.id        = counter++;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public static Long getCounter() {
        return counter;
    }

    public static void setCounter(Long counter) {
        User.counter = counter;
    }

    /**
     * @return Long User id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param Long id
     * Sets the User id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String User firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param String User firstname
     * Sets the User firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return String User lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param String User lastname
     * Sets the User lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return String User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param String User email
     * Sets the User email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return String User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param String User password
     * Sets the User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return  Map<Long, Activity> Acitivties
     */
    public Map<Long, Activity> getActivities() {
        return activities;
    }

    /**
     * @param Map<Long, Activity> Acitivties
     * Sets the User Acitivities Map
     */
    public void setActivities(Map<Long, Activity> activities) {
        this.activities = activities;
    }

    /**
     * @return String
     * String representation for the User model
     */
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

    /**
     * Equals method for determining object equality for the User model
     */
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

    /**
     * @return int
     * hashcode integer representation for the User model object instances.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.lastname, this.firstname, this.email, this.password, this.activities);
    }
}
package models;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Created by annadowling on 23/09/2016.
 * Class for modelling the structure of Activity and all of its associated relationships.
 */

public class Activity implements Serializable {

    static Long counter = 0l;
    public Long id;
    public String type;
    public String location;
    public double distance;
    public String starttime;
    public String duration;


    public List<Location> route = new ArrayList<Location>();

    /**
     * Empty constructor for Activity model.
     */
    public Activity() {
    }

    /**
     * Overloaded constructor for Activity model.
     */
    public Activity(String type, String location, double distance, String starttime, String duration) {
        this.id = counter++;
        this.type = type;
        this.location = location;
        this.distance = distance;
        this.starttime = starttime;
        this.duration = duration;
    }

    /**
     * Equals method for determining object equality for the Activity model
     */
    @Override
    public boolean equals(final Object obj)
    {
        if (obj instanceof Activity)
        {
            final Activity other = (Activity) obj;
            return Objects.equal(type, other.type)
                    && Objects.equal(location,  other.location)
                    && Objects.equal(distance,  other.distance)
                    && Objects.equal(starttime,     other.starttime)
                    && Objects.equal(duration,     other.duration)
                    && Objects.equal(route,     other.route);
        }
        else
        {
            return false;
        }
    }

    /**
     * @return String
     * String representation for the Activity model
     */
    @Override
    public String toString() {
        return toStringHelper(this)
                .addValue(id)
                .addValue(type)
                .addValue(location)
                .addValue(distance)
                .addValue(starttime)
                .addValue(duration)
                .addValue(route)
                .toString();
    }

    /**
     * @return int
     * hashcode integer representation for the Activity model object instances.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.type, this.location, this.distance, this.starttime, this.duration, this.route);
    }

    /**
     * @return Long Activity id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param Long id
     * Sets the Activity id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String Activity type
     */
    public String getType() {
        return type;
    }

    /**
     * @param String type
     * Sets the Activity type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return String Activity location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param String location
     * Sets the Activity location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return double Activity distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param double distance
     * Sets the Activity distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return double Activity duration
     */
    public String getDuration() {
        return duration;
    }

    /**
     * @param String duration
     * Sets the Activity duration
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * @return String Activity starttime
     */
    public String getStarttime() {
        return starttime;
    }

    /**
     * @param String starttime
     * Sets the Activity starttime
     */
    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    /**
     * @return List<Location>
     */
    public List<Location> getRoute() {
        return route;
    }

    /**
     * @param  List<Location>
     * Sets the Activity Location list.
     */
    public void setRoute(List<Location> route) {
        this.route = route;
    }

}

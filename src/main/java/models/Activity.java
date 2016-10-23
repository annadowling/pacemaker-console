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

    public Activity() {
    }

    public Activity(String type, String location, double distance, String starttime, String duration) {
        this.id = counter++;
        this.type = type;
        this.location = location;
        this.distance = distance;
        this.starttime = starttime;
        this.duration = duration;
    }

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

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.type, this.location, this.distance, this.starttime, this.duration, this.route);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public List<Location> getRoute() {
        return route;
    }

    public void setRoute(List<Location> route) {
        this.route = route;
    }

}

package models;

import com.google.common.base.Objects;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Created by annadowling on 23/09/2016.
 */

public class Activity {

    static Long counter = 0l;
    public Long id;
    public String type;
    public String location;
    public double distance;


    public List<Location> route = new ArrayList<Location>();

    public Activity() {
    }

    public Activity(String type, String location, double distance) {
        this.id = counter++;
        this.type = type;
        this.location = location;
        this.distance = distance;
    }

    public List<Location> getRoute() {
        return route;
    }

    public void setRoute(List<Location> route) {
        this.route = route;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .addValue(id)
                .addValue(type)
                .addValue(location)
                .addValue(distance)
                .addValue(route)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.type, this.location, this.distance, this.route);
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
}

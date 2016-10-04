package models;

import com.google.common.base.Objects;

import java.time.Duration;
import java.time.LocalDateTime;
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
    public LocalDateTime activityDate;
    public Duration activityDuration;


    public List<Location> route = new ArrayList<Location>();

    public Activity() {
    }

    public Activity(String type, String location, double distance, LocalDateTime activityDate, Duration activityDuration) {
        this.id = counter++;
        this.type = type;
        this.location = location;
        this.distance = distance;
        this.activityDate = activityDate;
        this.activityDuration = activityDuration;
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
                .addValue(activityDate)
                .addValue(activityDuration)
                .addValue(route)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.type, this.location, this.distance, this.activityDate, this.activityDuration, this.route);
    }

}

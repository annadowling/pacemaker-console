package models;/***************************************************************
 * Copyright (c) 2016 Errigal Inc.
 * <p>
 * This software is the confidential and proprietary information
 * of Errigal, Inc.  You shall not disclose such confidential
 * information and shall use it only in accordance with the
 * license agreement you entered into with Errigal.
 * <p>
 * **************************************************************
 */

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
    public Double distance;


    public List<Location> route = new ArrayList<>();

    public Activity() {
    }

    public Activity(String type, String location, Double distance) {
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

}

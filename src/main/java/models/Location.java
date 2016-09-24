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

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Created by annadowling on 23/09/2016.
 */

public class Location {

    static Long counter = 0l;
    public Long id;
    public Double latitude;
    public Double longitude;

    public Location() {
    }

    public Location(Double latitude, Double longitude) {
        this.id = counter++;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .addValue(id)
                .addValue(latitude)
                .addValue(longitude)
                .toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.latitude, this.longitude);
    }
}

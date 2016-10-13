package models;

import com.google.common.base.Objects;

import java.io.Serializable;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Created by annadowling on 23/09/2016.
 */

public class Location implements Serializable {

    static Long counter = 0l;

    public Long id;
    public float latitude;
    public float longitude;

    public Location() {
    }

    public Location(float latitude, float longitude) {
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}

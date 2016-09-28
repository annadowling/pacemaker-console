package models;

import com.google.common.base.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Created by annadowling on 23/09/2016.
 */

public class Location {

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
}

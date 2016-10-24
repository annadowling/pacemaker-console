package models;

import com.google.common.base.Objects;

import java.io.Serializable;

import static com.google.common.base.MoreObjects.toStringHelper;

/**
 * Created by annadowling on 23/09/2016.
 * Class for modelling the structure of Location and all of its associated relationships.
 */

public class Location implements Serializable {

    static Long counter = 0l;

    public Long id;
    public float latitude;
    public float longitude;

    /**
     * Empty constructor for Location model.
     */
    public Location() {
    }

    /**
     * Overloaded constructor for Location model.
     */
    public Location(float latitude, float longitude) {
        this.id = counter++;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Equals method for determining object equality for the Location model
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Location) {
            final Location other = (Location) obj;
            return Objects.equal(latitude, other.latitude)
                    && Objects.equal(longitude, other.longitude);
        } else {
            return false;
        }
    }

    /**
     * @return String
     * String representation for the Location model
     */
    @Override
    public String toString() {
        return toStringHelper(this)
                .addValue(id)
                .addValue(latitude)
                .addValue(longitude)
                .toString();
    }

    /**
     * @return int
     * hashcode integer representation for the Location model object instances.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(this.id, this.latitude, this.longitude);
    }

    /**
     * @return Long Location id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param Long id
     *             Sets the Location id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return float latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * @param float latitude
     *              Sets the latitude
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * @return float longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @param float longitude
     *              Sets the longitude
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}

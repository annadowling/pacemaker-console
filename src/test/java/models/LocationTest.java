package models;
import org.junit.Test;
import static org.junit.Assert.*;
import static models.Fixtures.locations;

/**
 * Created by annadowling on 11/10/2016.
 */

public class LocationTest {

    @Test
    public void testCreate()
    {
        assertEquals (0.01, 23.3f, locations[0].latitude);
        assertEquals (0.01, 33.3f, locations[0].longitude);
    }

    @Test
    public void testIds()
    {
        assertNotEquals(locations[0].id, locations[1].id);
    }

    @Test
    public void testToString()
    {
        assertEquals ("Location{" + locations[0].id + ", 23.3, 33.3}", locations[0].toString());
    }

    @Test
    public void testGetId(){
        assert locations[0].getId().equals(locations[0].id);
    }

    @Test
    public void testGetLongitude(){
        assert locations[0].getLongitude() == locations[0].longitude;
    }

    @Test
    public void testGetLatitude(){
        assert locations[0].getLatitude() == locations[0].latitude;
    }

    @Test
    public void testSetLongitude(){
        Location location = new Location(20.3f, 40.3f);
        location.setLongitude(28.4f);

        assert location.getLongitude() == 28.4f;
        assertNotEquals(location.getLongitude(), 40.3f);
    }

    @Test
    public void testSetLatitude(){
        Location location = new Location(44.3f, 80.3f);
        location.setLatitude(17.4f);

        assert location.getLatitude() == 17.4f;
        assertNotEquals(location.getLatitude(), 44.3f);
    }
}

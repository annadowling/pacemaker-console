package models;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by annadowling on 11/10/2016.
 */

public class LocationTest {

    private Location one;
    private Location two;

    @Before
    public void setup()
    {
//        one = new Location(23.3, 33.3);
//        two = new Location(34.4, 22.2);
    }

    @After
    public void tearDown()
    {
        one = two = null;
    }


    @Test
    public void testCreate()
    {
        Location one = new Location(23.3f, 33.3f);
        assertEquals (0.01, 23.3f, one.latitude);
        assertEquals (0.01, 33.3f, one.longitude);
    }

    @Test
    public void testIds()
    {
        Location one = new Location(23.3f, 33.3f);
        Location two = new Location(34.4f, 22.2f);
        assertNotEquals(one.id, two.id);

    }

    @Test
    public void testToString()
    {
        Location one = new Location(23.3f,33.3f);
        assertEquals ("Location{2, 23.3f, 33.3f}", one.toString());
    }
}

package models;

import org.junit.Test;
import utils.DateTimeParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import static org.junit.Assert.*;
import static models.Fixtures.users;

/**
 * Created by annadowling on 11/10/2016.
 */


public class UserTest
{

    User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");

    @Test
    public void testCreate()
    {
        assertEquals ("homer",               homer.firstname);
        assertEquals ("simpson",             homer.lastname);
        assertEquals ("homer@simpson.com",   homer.email);
        assertEquals ("secret",              homer.password);
    }

    @Test
    public void testIds()
    {
        Set<Long> ids = new HashSet<>();
        for (User user : users)
        {
            ids.add(user.id);
        }
        assertEquals (users.length, ids.size());
    }

    @Test
    public void testToString()
    {
        assertEquals ("User{" + homer.id + ", homer, simpson, secret, homer@simpson.com, {}}", homer.toString());
    }

    @Test
    public void testEquals()
    {
        User homer = new User ("homer", "simpson", "homer@simpson.com",  "secret");
        User homer2 = new User ("homer", "simpson", "homer@simpson.com",  "secret");
        User bart   = new User ("bart", "simpson", "bartr@simpson.com",  "secret");

        assertEquals(homer, homer);
        assertEquals(homer, homer2);
        assertNotEquals(homer, bart);
        assertSame(homer, homer);
        assertNotSame(homer, homer2);
    }

    @Test
    public void testGetFirstName(){
        assert homer.getFirstname().equals(homer.firstname);

    }

    @Test
    public void testGetLastName(){
        assert homer.getLastname().equals(homer.lastname);

    }

    @Test
    public void testGetEmail(){
        assert homer.getEmail().equals(homer.email);

    }

    @Test
    public void testGetPassword(){
        assert homer.getPassword().equals(homer.password);

    }

    @Test
    public void testGetId(){
        assert homer.getId().equals(homer.id);

    }

    @Test
    public void testGetActivities(){
        DateTimeParser parser = new DateTimeParser();
        Activity activity = new Activity ("walk",  "fridge", 0.001, parser.parseStringToDateTime("2016-08-10T12:55:05"), parser.parseDurationFromString("12:00:00"));
        Map<Long, Activity> activityMap = new HashMap<Long, Activity>();
        activityMap.put(homer.id, activity);

        homer.setActivities(activityMap);
        
        assertNotNull(homer.getActivities());
        assert homer.getActivities().size() == 1;
        assert homer.getActivities().equals(activityMap);
    }
}

package models;

import org.junit.Test;

import java.util.HashSet;
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
}

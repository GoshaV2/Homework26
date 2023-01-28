package model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void testCreationWithArguments() {
        User user = new User("login", "email");
        assertEquals("login", user.getLogin());
        assertEquals("email", user.getEmail());
    }

    @Test
    public void testCreationWithoutArguments() {
        User user = new User();
        assertNull(user.getLogin());
        assertNull(user.getEmail());
    }

    @Test
    public void testCorrectEmail() {
        final String correctEmail = "123@mail.ru";
        final String unCorrectEmail1 = "123mail.ru";
        final String unCorrectEmail2 = "123mailru";
        final String unCorrectEmail3 = "123ma@@il.ru";
        assertTrue(isCorrectEmail(new User("login", correctEmail).getEmail()));
        assertFalse(isCorrectEmail(new User("login", unCorrectEmail1).getEmail()));
        assertFalse(isCorrectEmail(new User("login", unCorrectEmail2).getEmail()));
        assertFalse(isCorrectEmail(new User("login", unCorrectEmail3).getEmail()));
    }

    @Test
    public void testCorrectLoginAndEmail() {
        final Pair<String, String> correctPair = new ImmutablePair<>("login", "email");
        final Pair<String, String> unCorrectPair = new ImmutablePair<>("email@mail.ru", "email@mail.ru");
        User user1 = new User(correctPair.getKey(), correctPair.getValue());
        User user2 = new User(unCorrectPair.getKey(), unCorrectPair.getValue());
        assertNotEquals(user1.getLogin(), user1.getEmail());
        assertEquals(user2.getLogin(), user2.getEmail());
    }

    private boolean isCorrectEmail(String email) {
        if (StringUtils.countMatches(email, '@') != 1 || StringUtils.countMatches(email, '.') < 1) {
            return false;
        }
        if (email.charAt(0) == '@' || email.charAt(0) == '.' || email.charAt(email.length() - 1) == '.') {
            return false;
        }
        if (email.indexOf('@') < email.lastIndexOf('.') + 1) {
            return true;
        }
        return false;
    }
}

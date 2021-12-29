package kz.noorball.junittesting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private static User user;
    private static User user1;
    private static User user2;

    private static User userNotAdd;
    private static User userNotAdd1;
    private static User userNotAdd2;

    @BeforeAll
    static void init() {
        User.allUsers = new HashMap<>();
        user = new User("Евгений", 35, Sex.MALE);
        user1 = new User("Марина", 34, Sex.FEMALE);
        user2 = new User("Алина", 7, Sex.FEMALE);

        userNotAdd = new User("", 0, null);
        userNotAdd1 = new User(null, 0, null);
        userNotAdd2 = new User("name", -1, Sex.MALE);
    }

    @Test
    void newUser_NOT_EMPTY() {
        if (User.getAllUsers().stream().anyMatch(user -> (user.getName() == null || "".equals(user.getName())))) {
            fail("Empty user creation attempt");
        }
    }

    @Test
    void newUser_NOT_AGE_AGE() {
        if (User.getAllUsers().stream().anyMatch(user -> (user.getAge() <= 0))) {
            fail("Empty user creation attempt");
        }
    }

    @Test
    void newUser_NOT_NULL_SEX() {
        if (User.getAllUsers().stream().anyMatch(user -> (user.getSex() == null))) {
            fail("Unacceptable sex user creation attempt");
        }
    }

    @Test
    void getAllUsers() {

        List<User> expected = User.getAllUsers();

        List<User> actual = new ArrayList<>();
        actual.add(user);
        actual.add(user1);
        actual.add(user2);

        assertEquals(expected, actual);
    }

    @Test
    void getAllUsers_NO_NULL() {
        List<User> expected = User.getAllUsers();

        assertNotNull(expected);
    }

    @Test
    void getAllUsers_MALE() {

        List<User> expected = User.getAllUsers(Sex.MALE);
        List<User> actual = new ArrayList<>();
        actual.add(user);

        assertEquals(expected, actual);
    }

    @Test
    void getAllUsers_MALE_NOT_NULL() {

        List<User> expected = User.getAllUsers(Sex.MALE);
        assertNotNull(expected);
    }

    @Test
    void getAllUsers_FEMALE() {

        List<User> expected = User.getAllUsers(Sex.FEMALE);
        List<User> actual = new ArrayList<>();
        actual.add(user1);
        actual.add(user2);

        assertEquals(expected, actual);
    }

    @Test
    void getAllUsers_FEMALE_NOT_NULL() {

        List<User> expected = User.getAllUsers(Sex.FEMALE);
        assertNotNull(expected);
    }

    @Test
    void getHowManyUsers() {
        long expected = User.getHowManyUsers();
        long actual = 3;
        assertEquals(expected, actual);
    }

    @Test
    void getHowManyUsers_MALE() {
        long expected = User.getHowManyUsers(Sex.MALE);
        long actual = 1;
        assertEquals(expected, actual);
    }

    @Test
    void getHowManyUsers_FEMALE() {
        long expected = User.getHowManyUsers(Sex.FEMALE);
        long actual = 2;
        assertEquals(expected, actual);
    }

    @Test
    void getAllAgeUsers() {
        long expected = User.getAllAgeUsers();
        long actual = 35 + 34 + 7;
        assertEquals(expected, actual);
    }

    @Test
    void getAllAgeUsers_MALE() {
        long expected = User.getAllAgeUsers(Sex.MALE);
        long actual = 35;
        assertEquals(expected, actual);
    }

    @Test
    void getAllAgeUsers_FEMALE() {
        long expected = User.getAllAgeUsers(Sex.FEMALE);
        long actual = 34 + 7;
        assertEquals(expected, actual);
    }

    @Test
    void getAverageAgeOfAllUsers() {
        long expected = User.getAverageAgeOfAllUsers();
        long actual = (35 + 34 + 7) / 3;
        assertEquals(expected, actual);
    }

    @Test
    void getAverageAgeOfAllUsers_MALE() {
        long expected = User.getAverageAgeOfAllUsers(Sex.MALE);
        long actual = 35;
        assertEquals(expected, actual);
    }

    @Test
    void getAverageAgeOfAllUsers_FEMALE() {
        long expected = User.getAverageAgeOfAllUsers(Sex.FEMALE);
        long actual = (34 + 7) / 2;
        assertEquals(expected, actual);
    }
}
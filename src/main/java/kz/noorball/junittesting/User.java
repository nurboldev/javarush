package kz.noorball.junittesting;


import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class User {
    public static Map<Integer, User> allUsers = new HashMap<>();
    private static int countId = 1;

    @EqualsAndHashCode.Exclude
    private int id;

    private String name;
    private int age;
    private Sex sex;

    User(String name, int age, Sex sex) {
        if (name == null || name.isEmpty() || age <= 0 || sex == null) {
            return;
        }
        this.id = countId;
        this.name = name;
        this.age = age;
        this.sex = sex;

        if (!hasUser(this)) {
            allUsers.put(++countId, this);
        }
    }

    private boolean hasUser(User user) {
        return getAllUsers().stream().anyMatch(u -> u.equals(user));
    }

    static List<User> getAllUsers() {
        return new ArrayList<>(allUsers.values());
    }

    static List<User> getAllUsers(Sex sex) {
        return allUsers.values().stream().filter(user -> user.getSex() == sex).collect(Collectors.toList());
    }

    static long getHowManyUsers() {
        return allUsers.size();
    }

    static long getHowManyUsers(Sex sex) {
        return allUsers.values().stream().filter(user -> user.getSex() == sex).count();
    }

    static long getAllAgeUsers() {
        return allUsers.values().stream().mapToLong(User::getAge).sum();
    }

    static long getAllAgeUsers(Sex sex) {
        return allUsers.values().stream().filter(user -> user.getSex() == sex).mapToLong(User::getAge).sum();
    }

    static long getAverageAgeOfAllUsers() {
        return getAllAgeUsers() / allUsers.size();
    }

    static long getAverageAgeOfAllUsers(Sex sex) {
        return getAllAgeUsers(sex) / getHowManyUsers(sex);
    }
}

enum  Sex {
    MALE, FEMALE
}
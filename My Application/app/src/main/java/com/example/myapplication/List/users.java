package com.example.myapplication.List;


import com.example.myapplication.Bean.User;

import java.util.HashMap;
import java.util.Map;

public class users {
    private static final Map<String, User> users = new HashMap<>();

    public static void addUser(User user) {
        users.put(user.getUsername(), user);
    }

    public static User getUserByUsername(String username) {
        return users.get(username);
    }

    public static boolean isUserExist(String username) {
        return users.containsKey(username);
    }
}
package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.models.Role;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.validators.UserInfoValidator;
import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String USER_DATA_PATH = "src/resources/UserData.json";

    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();

    public UserRepository() {
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        //TODO: return parsed list of Users from UserData.json
        return allUsers;
    }

    public Optional<User> findByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    public void save(User user) {
        List<User> allUsers = getAllUsers();

        Optional<User> existingUser = allUsers.stream()
                .filter(u -> u.getUsername().equals(user.getUsername()))
                .findFirst();

        if (!existingUser.isEmpty()) {
            throw new RuntimeException("User with name: " + user.getUsername() + " already exists!");
        }
        allUsers.add(user);

        //TODO: Write allUsers to UserData.json
        try (PrintWriter out = new PrintWriter(new FileWriter(USER_DATA_PATH))) {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String json = g.toJson(allUsers);
            System.out.println(json);
            out.append(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

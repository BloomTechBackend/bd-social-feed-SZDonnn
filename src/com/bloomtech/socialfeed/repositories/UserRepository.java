package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.validators.UserInfoValidator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class UserRepository {
    private static final String USER_DATA_PATH = "src/resources/UserData.json";

    private static final UserInfoValidator userInfoValidator = new UserInfoValidator();
    private List<User> allUsers = new ArrayList<>();

    /**
     * Empty constructor for UserRepository.
     */
    public UserRepository() {
    }

    /**
     * Method for getting all the users from json file.
     * @return list of users.
     */
    public List<User> getAllUsers() {
        //TODO: return parsed list of Users from UserData.json
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(USER_DATA_PATH));
            allUsers = Arrays.asList(gson.fromJson(reader, User[].class));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    /**
     * Filters users by username.
     * @param username - to be filtered if already exists.
     * @return the user if exist
     */
    public Optional<User> findByUsername(String username) {
        return getAllUsers()
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst();
    }

    /**
     * Save's the user and write the data to a json file.
     * @param user - add the user to users list if valid.
     */
    public void save(User user) {
        userInfoValidator.validate(user);
        allUsers.add(user);
        //TODO: Write allUsers to UserData.json
        try (PrintWriter out = new PrintWriter(new FileWriter(USER_DATA_PATH))) {
            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String json = g.toJson(allUsers);
            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        } // working
    }
}

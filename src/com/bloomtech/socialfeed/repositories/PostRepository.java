package com.bloomtech.socialfeed.repositories;

import com.bloomtech.socialfeed.helpers.LocalDateTimeAdapter;
import com.bloomtech.socialfeed.models.Post;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PostRepository {
    private static final String POST_DATA_PATH = "src/resources/PostData.json";
    private List<Post> allPosts = new ArrayList<>();

    /**
     * An empty constructor for PostRepository.
     */
    public PostRepository() {
    }

    /**
     * Displays a list of posts from json file.
     * @return a list of all posts
     */
    public List<Post> getAllPosts() {
        //TODO: return all posts from the PostData.json file
        try {
            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).create();
            Reader reader = Files.newBufferedReader(Paths.get(POST_DATA_PATH));
            allPosts = Arrays.asList(gson.fromJson(reader, Post[].class));
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPosts;
    }

    /**
     * Finding post by username.
     * @param username - if posts exist under this username
     * @return - list if username is found
     */
    public List<Post> findByUsername(String username) {
        return getAllPosts()
                .stream()
                .filter(p -> p.getUsername().equals(username))
                .collect(Collectors.toList());
    }

    /**
     * Add the post to a list and write it to a json file.
     * @param post - post to be added.
     * @return the list of the posts.
     */
    public List<Post> addPost(Post post) {
        allPosts.add(post);
        //TODO: Write the new Post data to the PostData.json file
        try (PrintWriter out = new PrintWriter(new FileWriter(POST_DATA_PATH))) {
            Gson g = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                    .setPrettyPrinting().create();
            String json = g.toJson(allPosts);
            out.write(json);
        } catch (Exception e) {
            e.printStackTrace();
        } // working
        //TODO: Return an updated list of all posts
        return allPosts;
    }
}

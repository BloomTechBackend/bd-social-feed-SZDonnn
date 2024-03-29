package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;
import com.bloomtech.socialfeed.repositories.PostRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class SourceFeed implements Source {
    private final PostRepository postRepository = new PostRepository();
    private List<Post> posts;
    private List<Observer> observers;


    /**
     * Constructor for SourceFeed class.
     */
    public SourceFeed() {
        posts = new ArrayList<>();
        observers = new ArrayList<>();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void updateAll() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * Add's a post under a user.
     * @param user owner of the post
     * @param body content of a post
     * @return posted by the user
     */
    public Post addPost(User user, String body) {
        Post post = new Post(user.getUsername(),
                LocalDateTime.now(),
                body);
        posts = postRepository.addPost(post);
        attach(user.getUserFeed());
        updateAll();
        return post;
    }

    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Get all posts from users.
     */
    public void getAllPosts() {
        postRepository.getAllPosts();
    }

    public List<Observer> getObservers() {
        return observers;
    }
}

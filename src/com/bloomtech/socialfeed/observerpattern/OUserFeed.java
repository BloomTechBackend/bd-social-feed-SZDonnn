package com.bloomtech.socialfeed.observerpattern;

import com.bloomtech.socialfeed.App;
import com.bloomtech.socialfeed.models.Post;
import com.bloomtech.socialfeed.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO: Implement Observer Pattern
public class OUserFeed implements Observer {
    private User user;
    private List<Post> feed;

    /**
     * Constructor for OUserFeed.
     * @param user that owns the feed.
     */
    public OUserFeed(User user) {
        this.user = user;
        //TODO: update OUserFeed in constructor after implementing observer pattern
        this.feed = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    @Override
    public void update() {
        if (user != null) {
            feed = App.sourceFeed.getPosts();
        }
    }

    /**
     * Returns a list of feed/posts.
     * @return list of post/feed
     */
    public List<Post> getFeed() {
        List<Post> posts = new ArrayList<>();
        List<String> following = user.getFollowing();
        for (Post post : feed) {
            for (String followed : following) {
                if (followed.equalsIgnoreCase(post.getUsername())) {
                    posts.add(post);
                }
            }
        }
        return posts;
    }
}

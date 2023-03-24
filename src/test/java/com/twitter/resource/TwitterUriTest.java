package com.twitter.resource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TwitterUriTest {
    @Test
    public void testUsersUri() {
        String expectedUri = "/users";
        String actualUri = TwitterUri.USERS;

        Assertions.assertEquals(expectedUri, actualUri);
    }

    @Test
    public void testPostsUri() {
        String expectedUri = "/posts";
        String actualUri = TwitterUri.POSTS;

        Assertions.assertEquals(expectedUri, actualUri);
    }

    @Test
    public void testTwitterUri() {
        TwitterUri twitterUri = new TwitterUri();
        // Call any method on the class to ensure its code is executed
        twitterUri.getClass();
    }
}

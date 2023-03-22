package com.twitter.representation;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RetweetRepresentationTest {

    @Test
    public void testConstructor() {
        Integer id = 1;
        LocalDateTime dateTime = LocalDateTime.now();
        PostRepresentation originalPost = new PostRepresentation();
        Set<UserBasicRepresentation> likes = new HashSet<>();
        UserRepresentation user = new UserRepresentation();
        Set<PostRepresentation> replies = new HashSet<>();
        Set<PostRepresentation> retweets = new HashSet<>();

        RetweetRepresentation retweet = new RetweetRepresentation();
        retweet.id = id;
        retweet.dateTime = dateTime;
        retweet.originalPost = originalPost;
        retweet.likes = likes;
        retweet.user = user;
        retweet.replies = replies;
        retweet.retweets = retweets;

        assertNotNull(retweet);
        assertEquals(id, retweet.id);
        assertEquals(dateTime, retweet.dateTime);
        assertEquals(originalPost, retweet.originalPost);
        assertEquals(likes, retweet.likes);
        assertEquals(user, retweet.user);
        assertEquals(replies, retweet.replies);
        assertEquals(retweets, retweet.retweets);
    }

}

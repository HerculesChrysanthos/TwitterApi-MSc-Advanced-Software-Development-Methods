package com.twitter.representation;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

class TweetRepresentationTest {

    @Test
    void testConstructor() {
        // Given
        TweetRepresentation tweet = new TweetRepresentation();

        // Then
        assertNotNull(tweet);
    }

    @Test
    void testFields() {
        // Given
        Integer id = 123;
        LocalDateTime dateTime = LocalDateTime.now();
        TweetBodyRepresentation content = new TweetBodyRepresentation();
        content.tweetContent = "Hello, world!";
        UserRepresentation user = new UserRepresentation();
        Set<UserBasicRepresentation> likes = new HashSet<>();
        UserBasicRepresentation Alice = new UserBasicRepresentation();
        UserBasicRepresentation Bob = new UserBasicRepresentation();
        Alice.username = "Alice";
        Bob.username = "Bob";
        likes.add(Alice);
        likes.add(Bob);
        Set<PostRepresentation> replies = new HashSet<>();
        replies.add(new PostRepresentation());
        replies.add(new PostRepresentation());
        Set<PostRepresentation> retweets = new HashSet<>();
        retweets.add(new PostRepresentation());

        TweetRepresentation tweet = new TweetRepresentation();

        // When
        tweet.id = id;
        tweet.dateTime = dateTime;
        tweet.content = content;
        tweet.user = user;
        tweet.likes = likes;
        tweet.replies = replies;
        tweet.retweets = retweets;

        // Then
        assertEquals(id, tweet.id);
        assertEquals(dateTime, tweet.dateTime);
        assertEquals(content, tweet.content);
        assertEquals(user, tweet.user);
        assertEquals(likes, tweet.likes);
        assertEquals(replies, tweet.replies);
        assertEquals(retweets, tweet.retweets);
    }
}

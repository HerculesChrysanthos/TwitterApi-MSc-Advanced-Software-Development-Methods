package com.twitter.representation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class ReplyRepresentationTest {

    @Test
    public void testConstructor() {
        Integer id = 1;
        LocalDateTime dateTime = LocalDateTime.now();
        TweetBodyRepresentation content = new TweetBodyRepresentation();
        content.tweetContent = "test tweet";

        DateOfBirthRepresentation dob = new DateOfBirthRepresentation();
        dob.year = 2000;
        dob.month = 2;
        dob.day = 1;

        PostRepresentation parentPost = new PostRepresentation();
        parentPost.id = 123;

        UserRepresentation user = new UserRepresentation();
        user.id = 1000;
        user.password = "mypass";
        user.username = "lakis";
        user.dateOfBirth = dob;

        Set<UserBasicRepresentation> likes = new HashSet<>();
        Set<PostRepresentation> replies = new HashSet<>();
        Set<PostRepresentation> retweets = new HashSet<>();

        ReplyRepresentation reply = new ReplyRepresentation();
        reply.id = id;
        reply.dateTime = dateTime;
        reply.content = content;
        reply.parentPost = parentPost;
        reply.user = user;
        reply.likes = likes;
        reply.replies = replies;
        reply.retweets = retweets;

        assertNotNull(reply);
        assertEquals(id, reply.id);
        assertEquals(dateTime, reply.dateTime);
        assertEquals(content, reply.content);
        assertEquals(content.tweetContent, reply.content.tweetContent);
        assertEquals(parentPost, reply.parentPost);
        assertEquals(user, reply.user);
        assertEquals(user.id, reply.user.id);
        assertEquals(user.username, reply.user.username);
        assertEquals(user.dateOfBirth, reply.user.dateOfBirth);
        assertEquals(user.password, reply.user.password);
        assertEquals(user.email, reply.user.email);
        assertEquals(likes, reply.likes);
        assertEquals(replies, reply.replies);
        assertEquals(retweets, reply.retweets);
    }
}

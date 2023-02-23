package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetBodyTest {

    @Test
    public void testDefaultConstructor() {
        TweetBody tweetBody = new TweetBody();
        Assertions.assertNull(tweetBody.getTweetBody());
    }

    @Test
    public void testConstructorWithValidTweetBody() {
        String validTweetBody = "Hello, world!";
        TweetBody tweetBody = new TweetBody(validTweetBody);

        Assertions.assertEquals(validTweetBody, tweetBody.getTweetBody());
    }

    @Test
    public void testIsValidWithValidTweetBody() {
        String validTweetBody = "Hello, world!";
        TweetBody tweetBody = new TweetBody();

        Assertions.assertTrue(tweetBody.isValid(validTweetBody));
    }

    @Test
    public void testConstructorWithInvalidTweetBody() {
        String invalidTweetBody = "This tweet body is too long for the max length allowed";
        TweetBody tweetBody = new TweetBody(invalidTweetBody);

        Assertions.assertNull(tweetBody.getTweetBody());
    }

    @Test
    public void testEquals() {
        String tweetBodyText = "Hello, world!";
        TweetBody tweetBody1 = new TweetBody(tweetBodyText);
        TweetBody tweetBody2 = new TweetBody(tweetBodyText);
        TweetBody tweetBody3 = new TweetBody("Another tweet body");

        Assertions.assertEquals(tweetBody1, tweetBody2);
        Assertions.assertNotEquals(tweetBody1, tweetBody3);
    }

    @Test
    public void testHashCode() {
        String tweetBodyText = "Hello, world!";
        TweetBody tweetBody1 = new TweetBody(tweetBodyText);
        TweetBody tweetBody2 = new TweetBody(tweetBodyText);

        Assertions.assertEquals(tweetBody1.hashCode(), tweetBody2.hashCode());
    }

    @Test
    public void testSetAndGetTweetBody(){
        TweetBody tweetBody = new TweetBody();
        tweetBody.setTweetBody("This is a tweet body");

        Assertions.assertEquals("This is a tweet body", tweetBody.getTweetBody());
    }
}

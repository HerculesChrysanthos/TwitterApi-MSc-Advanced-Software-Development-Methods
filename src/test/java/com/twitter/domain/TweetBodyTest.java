package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetBodyTest {

    @Test
    public void testDefaultConstructor() {
        TweetBody tweetContent = new TweetBody();
        Assertions.assertNull(tweetContent.getTweetContent());
    }

    @Test
    public void testConstructorWithValidTweetBody() {
        String validTweetBody = "Hello, world!";
        TweetBody tweetContent = new TweetBody(validTweetBody);

        Assertions.assertEquals(validTweetBody, tweetContent.getTweetContent());
    }

    @Test
    public void testIsValidWithValidTweetBody() {
        String validTweetBody = "Hello, world!";
        TweetBody tweetContent = new TweetBody();

        Assertions.assertTrue(tweetContent.isValid(validTweetBody));
    }

    @Test
    public void testConstructorWithInvalidTweetBody() {
        String invalidTweetBody = "This tweet body is too long for the max length allowed";
        TweetBody tweetContent = new TweetBody(invalidTweetBody);

        Assertions.assertNull(tweetContent.getTweetContent());
    }

    @Test
    public void testEquals() {
        String tweetContentText = "Hello, world!";
        TweetBody tweetContent1 = new TweetBody(tweetContentText);
        TweetBody tweetContent2 = new TweetBody(tweetContentText);
        TweetBody tweetContent3 = new TweetBody("Another tweet body");

        Assertions.assertEquals(tweetContent1, tweetContent2);
        Assertions.assertNotEquals(tweetContent1, tweetContent3);
    }

    @Test
    public void testHashCode() {
        String tweetContentText = "Hello, world!";
        TweetBody tweetContent1 = new TweetBody(tweetContentText);
        TweetBody tweetContent2 = new TweetBody(tweetContentText);

        Assertions.assertEquals(tweetContent1.hashCode(), tweetContent2.hashCode());
    }

    @Test
    public void testSetAndGetTweetBody(){
        TweetBody tweetContent = new TweetBody();
        tweetContent.setTweetContent("This is a tweet body");

        Assertions.assertEquals("This is a tweet body", tweetContent.getTweetContent());
    }
}

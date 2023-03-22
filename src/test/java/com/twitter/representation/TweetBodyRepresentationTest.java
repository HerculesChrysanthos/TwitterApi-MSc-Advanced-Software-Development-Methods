package com.twitter.representation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetBodyRepresentationTest {

    @Test
    public void testTweetBodyRepresentationConstructor() {
        TweetBodyRepresentation tweetBodyRepresentation = new TweetBodyRepresentation();
        Assertions.assertNotNull(tweetBodyRepresentation);
    }

    @Test
    public void testTweetBodyRepresentationField() {
        TweetBodyRepresentation tweetBodyRepresentation = new TweetBodyRepresentation();
        tweetBodyRepresentation.tweetContent = "Hello, World!";
        Assertions.assertEquals("Hello, World!", tweetBodyRepresentation.tweetContent);
    }
}

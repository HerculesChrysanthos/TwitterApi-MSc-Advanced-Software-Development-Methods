package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetTest {

    @Test
    public void testSetAndGetTweetBody(){
        TweetBody tweetBody = new TweetBody("This is a tweet body");
        Tweet tweet = new Tweet();
        tweet.setTweetBody(tweetBody);

        Assertions.assertEquals(tweetBody, tweet.getTweetBody());
    }
}

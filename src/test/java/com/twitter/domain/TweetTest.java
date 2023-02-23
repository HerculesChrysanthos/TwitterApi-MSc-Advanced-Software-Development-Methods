package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TweetTest {

    @Test
    public void setAndGetTweetBody(){
        TweetBody tweetBody = new TweetBody("This is a tweet body");
        Tweet tweet = new Tweet();
        tweet.setTweetBody(tweetBody);

        Assertions.assertEquals(tweet.getTweetBody(), tweetBody);
    }
}

package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RetweetTest {

    @Test
    public void testDefaultConstructor() {
        Retweet retweet = new Retweet();
        Assertions.assertNull(retweet.getOriginalPost());
        Assertions.assertNull(retweet.getUser());
    }

    @Test
    public void testSetAndGetParentPost(){
        Tweet tweet = new Tweet(new TweetBody("This is a tweet"));
        Retweet retweet = new Retweet();
        retweet.setOriginalPost(tweet);
        Assertions.assertEquals(tweet, retweet.getOriginalPost());
    }
}

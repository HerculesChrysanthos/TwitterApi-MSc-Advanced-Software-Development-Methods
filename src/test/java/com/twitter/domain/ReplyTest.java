package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReplyTest {

    @Test
    public void setAndGetContent(){
        TweetBody tweetBody = new TweetBody("This is a tweet");
        Reply reply = new Reply();
        reply.setContent(tweetBody);

        Assertions.assertEquals(reply.getContent().getTweetBody(), "This is a tweet");
    }

    @Test
    public void setAndGetParentPost(){
        Tweet tweet = new Tweet(new TweetBody("This is a tweet"));
        Reply reply = new Reply();
        reply.setParentPost(tweet);
        Assertions.assertEquals(reply.getParentPost(), tweet);
    }

}

package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReplyTest {
    @Test
    public void testDefaultConstructor() {
        Reply reply = new Reply();
        Assertions.assertNull(reply.getContent());
        Assertions.assertNull(reply.getParentPost());
        Assertions.assertNull(reply.getUser());
    }

    @Test
    public void testSetAndGetContent(){
        TweetBody tweetBody = new TweetBody("This is a tweet");
        Reply reply = new Reply();
        reply.setContent(tweetBody);

        Assertions.assertEquals("This is a tweet", reply.getContent().getTweetBody());
    }

    @Test
    public void testSetAndGetParentPost(){
        Tweet tweet = new Tweet(new TweetBody("This is a tweet"));
        Reply reply = new Reply();
        reply.setParentPost(tweet);
        Assertions.assertEquals(tweet, reply.getParentPost());
    }

}

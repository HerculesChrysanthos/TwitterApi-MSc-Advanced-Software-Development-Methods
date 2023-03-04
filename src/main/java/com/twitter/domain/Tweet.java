package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TWEET")
public class Tweet extends Post {

    @Column(name = "content", length = 50, nullable = false)
    private TweetBody content;

    public Tweet() { }

    public Tweet(TweetBody content) {
        this.content = content;
    }

    public TweetBody getTweetBody() {
        return content;
    }

    public void setTweetBody(TweetBody tweetContent) {
        this.content = tweetContent;
    }
}

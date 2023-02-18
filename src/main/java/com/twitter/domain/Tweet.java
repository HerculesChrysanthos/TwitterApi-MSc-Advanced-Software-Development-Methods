package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TWEET")
public class Tweet extends Post {

    @Column(name = "content", length = 50, nullable = false)
    private String content;

    public Tweet() { }

    public Tweet(String content) {
        this.content = content;

    }

    public String getTweetBody() {
        return content;
    }

    public void setTweetBody(String tweetBody) {
        this.content = tweetBody;
    }
}

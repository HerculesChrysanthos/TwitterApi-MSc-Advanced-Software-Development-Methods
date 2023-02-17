package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("TWEET")
public class Tweet extends Post {

    @Column(name = "content", length = 50, nullable = false)
    private TweetBody content;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Tweet() { }

    public Tweet(TweetBody content) {
        this.content = content;

    }

    public TweetBody getTweetBody() {
        return content;
    }

    public void setTweetBody(TweetBody tweetBody) {
        this.content = tweetBody;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.twitter.domain;

import com.sun.istack.NotNull;
import com.twitter.domain.Tweet;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class TweetBody {
    @NotNull
    @Column(name = "tweetBody", length = 50)
    private String tweetBody;

    @NotNull
    @Column(name = "maxNumOfChars")
    private final Integer maxNumOfChars = 50;

    public TweetBody() { }

    public TweetBody(String tweetBody) {
        if (isValid(tweetBody)) {
            this.tweetBody = tweetBody;
        }
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public void setTweetBody(String tweetBody) {
        this.tweetBody = tweetBody;
    }

    public boolean isValid(String tweetBody) {
        if (tweetBody.length() > maxNumOfChars) {
            return false;
        } else {
            return true;
        }
    }
}

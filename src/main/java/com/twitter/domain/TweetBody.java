package com.twitter.domain;

import com.twitter.domain.Tweet;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class TweetBody {
    @Column(name = "tweetBody", length = 50, nullable = false)
    private String tweetBody;
    @Column(name = "maxNumOfChars", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetBody tweetBody1 = (TweetBody) o;
        return tweetBody.equals(tweetBody1.tweetBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetBody);
    }
}

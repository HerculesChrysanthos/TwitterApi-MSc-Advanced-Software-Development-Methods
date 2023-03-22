package com.twitter.domain;

import com.sun.istack.NotNull;
import com.twitter.domain.Tweet;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class TweetBody {
    @NotNull
    @Column(name = "tweetContent", length = 50)
    private String tweetContent;

    @NotNull
    @Column(name = "maxNumOfChars")
    private final Integer maxNumOfChars = 50;

    public TweetBody() { }

    public TweetBody(String tweetContent) {
        if (isValid(tweetContent)) {
            this.tweetContent = tweetContent;
        }
    }

    public String getTweetContent() {
        return tweetContent;
    }

    public void setTweetContent(String tweetContent) {
        if (isValid(tweetContent)) {
            this.tweetContent = tweetContent;
        }
    }

    public boolean isValid(String tweetContent) {
        if (tweetContent.length() > maxNumOfChars) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TweetBody tweetContent1 = (TweetBody) o;
        return tweetContent.equals(tweetContent1.tweetContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tweetContent);
    }
}

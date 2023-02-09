package com.twitter.domain;

import com.twitter.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tweets")
public class Tweet {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "tweetBody", length = 50, nullable = false)
    private String tweetBody;

    @Column(name = "dateTime", nullable = false)
    private LocalDate dateTime = SystemDate.now();

    @Column(name = "retweetCount")
    private Integer retweetCount;

    @Column(name = "likeCount")
    private Integer likeCount;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    public Tweet() { }

    public Tweet(String tweetBody) {
        this.tweetBody = tweetBody;
        this.retweetCount = 0;
        this.likeCount = 0;
    }

    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public void setTweetBody(String tweetBody) {
        this.tweetBody = tweetBody;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

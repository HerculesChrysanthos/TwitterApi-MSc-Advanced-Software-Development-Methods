package com.twitter.domain;

import com.twitter.util.SystemDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "posts")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "postType", discriminatorType = DiscriminatorType.STRING)
public abstract class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private LocalDate dateTime = SystemDate.now();

    @Column(name = "retweetCount")
    private Integer retweetCount;

    @Column(name = "replyCount")
    private Integer replyCount;

    @Column(name = "likeCount")
    private Integer likeCount;

    @ManyToOne( fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(mappedBy = "likedPosts", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private Set<User> likes = new HashSet<User>();

    @OneToMany(mappedBy = "reply", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Reply> replies = new HashSet<Reply>();

    @OneToMany(mappedBy = "retweet", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Retweet> retweets = new HashSet<Retweet>();

    public Post(){

    }

    public Post(User user) {
        this.user = user;
        this.retweetCount = 0;
        this.replyCount = 0;
        this.likeCount = 0;
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

    public Integer getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(Integer retweetCount) {
        this.retweetCount = retweetCount;
    }

    public Integer getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(Integer replyCount) {
        this.replyCount = replyCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Set<User> getLikes() {
        return likes;
    }

    public void setLikes(Set<User> likes) {
        this.likes = likes;
    }

    public Set<Reply> getReplies() {
        return replies;
    }

    public void setReplies(Set<Reply> replies) {
        this.replies = replies;
    }

    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public void setRetweets(Set<Retweet> retweets) {
        this.retweets = retweets;
    }
}

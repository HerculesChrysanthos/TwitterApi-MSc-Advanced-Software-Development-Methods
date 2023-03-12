package com.twitter.domain;

import com.twitter.util.SystemDateTime;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "createdAt")
    private LocalDateTime dateTime = SystemDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    // if you delete a like, you don't delete the user
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "user_posts",
            joinColumns = { @JoinColumn(name = "postId")},
            inverseJoinColumns = { @JoinColumn(name = "userId")}
            )
    private Set<User> likes = new HashSet<User>();

    //  if you delete a post, you also delete and the reply
    @OneToMany(mappedBy = "parentPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reply> replies = new HashSet<Reply>();

    //  if you delete a post, you don't delete the retweet
    @OneToMany(mappedBy = "originalPost", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Retweet> retweets = new HashSet<Retweet>();

    public Post(){ }

    public Post(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<User> getLikes() {
        return likes;
    }
    public Set<Reply> getReplies() {
        return replies;
    }

    public void addReply(User user, TweetBody content) {
        Reply newReply = new Reply(user, this, content);
        this.replies.add(newReply);
    }

    public void addRetweet(User user) {
        Retweet newRetweet = new Retweet(user, this);
        this.retweets.add(newRetweet);
    }

    public Set<Retweet> getRetweets() {
        return retweets;
    }

    public boolean addLike(User user) {
        if (likes.contains(user)) {
            return false;
        }
        this.likes.add(user);
        return true;
    }

    public boolean removeLike(User user) {
        if (!likes.contains(user)) {
            return false;
        }
        likes.remove(user);
        return true;
    }
}

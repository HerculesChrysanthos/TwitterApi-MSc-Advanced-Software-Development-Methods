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

    @Column(name = "createdAt")
    private LocalDate dateTime = SystemDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(mappedBy = "likedPosts", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<User> likes = new HashSet<User>();

    // if you delete a post, you also delete and the reply
    @OneToMany(mappedBy = "parentPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Reply> replies = new HashSet<Reply>();

    // if you delete a post, you don't delete the retweet
    @OneToMany(mappedBy = "originalPost", fetch = FetchType.LAZY, cascade = CascadeType.ALL) //{CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Retweet> retweets = new HashSet<Retweet>();

    public Post(){

    }

    public Post(User user) {
        this.user = user;
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

    public Set<User> getLikes() {
        return likes;
    }
    public Set<Reply> getReplies() {
        return replies;
    }

    public void addReply(Reply newReply) {
        this.replies.add(newReply);
    }

    public void addRetweet(Retweet newRetweet) {
        this.retweets.add(newRetweet);
    }

    @Override
    public String toString() {
        return "Post{" +
                "dateTime=" + dateTime +
                ", user=" + user +
                '}';
    }
}

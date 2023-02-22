package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RETWEET")
public class Retweet extends Post{
    // if you delete a retweet, you don't delete the post but only you update its set of retweets
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "postId")
    private Post originalPost;

    public Retweet() { }

    public Retweet(User user, Post originalPost) {
        super(user);
        this.originalPost = originalPost;
    }

    public Post getRetweet() {
        return originalPost;
    }
}

package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RETWEET")
public class Retweet extends Post{

    // if you delete a retweet, you don't delete the post but only you update its set of replies
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "OriginalPostId")
    private Post originalPost;

    public Retweet() { }

    public Retweet(User user, Post originalPost) {
        super(user);
        this.originalPost = originalPost;
    }

    public Post getOriginalPost() {
        return originalPost;
    }

    public void setOriginalPost(Post originalPost) {
        this.originalPost = originalPost;
    }
}

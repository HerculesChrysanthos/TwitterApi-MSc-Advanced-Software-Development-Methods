package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RETWEET")
public class Retweet extends Post{

    // if you delete a retweet, you don't delete the post but only you update its set of replies
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "postId")
    private Post parentPost;

    public Retweet() { }

    public Retweet(User user, Post parentPost) {
        super(user);
        this.parentPost = parentPost;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }
}

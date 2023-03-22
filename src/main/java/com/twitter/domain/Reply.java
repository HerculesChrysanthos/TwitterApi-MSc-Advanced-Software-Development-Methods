package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("REPLY")
public class Reply extends Post {

    @Column(name = "content", length = 50, nullable = false)
    private TweetBody content;

    // if you delete a reply, you don't delete the post but only you update its set of replies
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "ParentPostId")
    private Post parentPost;

    protected Reply() { }

    public Reply(User user, Post parentPost, TweetBody content) {
        super(user);
        this.parentPost = parentPost;
        this.content = content;
    }

    public TweetBody getContent() {
        return content;
    }

    public void setContent(TweetBody content) {
        this.content = content;
    }

    public Post getParentPost() {
        return parentPost;
    }

    public void setParentPost(Post parentPost) {
        this.parentPost = parentPost;
    }
}
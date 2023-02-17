package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("REPLY")
public class Reply extends Post {

    @Column(name = "content", length = 50, nullable = false)
    private TweetBody content;

    // if you delete a reply, you don't delete the post but only you update its set of replies
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "postId")
    private Post post;

    public Reply() {

    }

    public Reply(User user, TweetBody content, Post post) {
        super(user);
        this.content = content;
        this.post = post;
    }

    public TweetBody getContent() {
        return content;
    }

    public void setContent(TweetBody content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
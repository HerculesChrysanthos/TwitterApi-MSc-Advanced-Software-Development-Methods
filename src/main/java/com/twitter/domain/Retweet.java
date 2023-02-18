package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RETWEET")
public class Retweet extends Post{

    // if you delete a retweet, you don't delete the post but only you update its set of retweets
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "postId")
    private Post retweet;

    public Retweet(){

    }

//    public Retweet(User user, TweetBody tweetBody, Post post) {
//        super(user);
//        this.retweet = post;
//    }

    public Post getRetweet() {
        return retweet;
    }

    public void setRetweet(Post retweet) {
        this.retweet = retweet;
    }
}

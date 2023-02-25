package com.twitter.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("RETWEET")
public class Retweet extends Post{
    public Retweet() { }

    public Retweet(User user) {
        super(user);
    }
}

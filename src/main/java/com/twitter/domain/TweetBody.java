package com.twitter.domain;

import javax.persistence.Column;

public class TweetBody {

    @Column(name = "body", length = 50, nullable = false, unique = true)
    private String body;

}

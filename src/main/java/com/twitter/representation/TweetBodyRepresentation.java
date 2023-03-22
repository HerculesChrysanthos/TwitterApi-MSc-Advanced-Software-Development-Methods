package com.twitter.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class TweetBodyRepresentation {
    public String tweetContent;

    public TweetBodyRepresentation() { }
}

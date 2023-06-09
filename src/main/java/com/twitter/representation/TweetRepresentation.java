package com.twitter.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;
import java.util.Set;

@RegisterForReflection
public class TweetRepresentation {
    public Integer id;
    public LocalDateTime dateTime;
    public TweetBodyRepresentation content;
    public Set<UserBasicRepresentation> likes;
    public UserRepresentation user;
    public Set<PostRepresentation> replies;
    public Set<PostRepresentation> retweets;

    public TweetRepresentation() { }
}

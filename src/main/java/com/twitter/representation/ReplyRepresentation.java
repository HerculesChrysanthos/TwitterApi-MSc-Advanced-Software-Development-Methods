package com.twitter.representation;

import com.twitter.domain.Post;
import com.twitter.domain.TweetBody;
import com.twitter.domain.User;
import io.quarkus.runtime.annotations.RegisterForReflection;

import java.time.LocalDateTime;

@RegisterForReflection
public class ReplyRepresentation {
    public Integer id;
    public LocalDateTime dateTime;
    public TweetBodyRepresentation content;
    public PostRepresentation parentPost;

    public UserRepresentation user;
}

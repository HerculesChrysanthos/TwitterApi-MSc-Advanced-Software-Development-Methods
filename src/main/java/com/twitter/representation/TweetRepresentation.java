package com.twitter.representation;

import com.twitter.domain.TweetBody;
import io.quarkus.runtime.annotations.RegisterForReflection;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@RegisterForReflection
public class TweetRepresentation {
    public Integer id;
    public LocalDateTime dateTime;
    public TweetBodyRepresentation content;

    public UserRepresentation user;
}

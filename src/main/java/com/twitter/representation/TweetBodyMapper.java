package com.twitter.representation;

import com.twitter.domain.TweetBody;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TweetBodyMapper {
    public abstract TweetBodyRepresentation toRepresentation(TweetBody tweetBody);
}

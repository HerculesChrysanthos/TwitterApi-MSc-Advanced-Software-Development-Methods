package com.twitter.representation;

import com.twitter.domain.TweetBody;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TweetBodyMapper {
    public abstract TweetBodyRepresentation toRepresentation(TweetBody tweetBody);

    public abstract TweetBody toModel(TweetBodyRepresentation tweetBodyRepresentation);
}

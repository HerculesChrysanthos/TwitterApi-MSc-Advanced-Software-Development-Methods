package com.twitter.representation;

import com.twitter.domain.Tweet;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class TweetMapper {
    public abstract TweetRepresentation toRepresentation(Tweet tweet);
}

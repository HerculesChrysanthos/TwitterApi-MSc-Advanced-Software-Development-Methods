package com.twitter.representation;

import com.twitter.domain.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {TweetBodyMapper.class, UserMapper.class})
public abstract class PostMapper {

    @Mapping(target = "content", source = "tweetBody")
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.dateOfBirth", ignore = true)
    public abstract TweetRepresentation toTweetRepresentation(Tweet tweet);

    @Mapping(target = "parentPost", source = "parentPost")
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.dateOfBirth", ignore = true)
    public abstract ReplyRepresentation toReplyRepresentation(Reply reply);

    @Mapping(target = "originalPost", source = "originalPost")
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.dateOfBirth", ignore = true)
    public abstract RetweetRepresentation toRetweetRepresentation(Retweet retweet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "tweetBody", source = "content")
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.dateOfBirth", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "retweets", ignore = true)
    public abstract Tweet toTweetModel(TweetRepresentation tweetRepresentation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "parentPost", ignore = true)
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.dateOfBirth", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "retweets", ignore = true)
    public abstract Reply toReplyModel(ReplyRepresentation replyRepresentation);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "originalPost", ignore = true)
    @Mapping(target = "user.id", source = "user.id")
    @Mapping(target = "user.username", ignore = true)
    @Mapping(target = "user.password", ignore = true)
    @Mapping(target = "user.email", ignore = true)
    @Mapping(target = "user.dateOfBirth", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "replies", ignore = true)
    @Mapping(target = "retweets", ignore = true)
    public abstract Retweet toRetweetModel(RetweetRepresentation retweetRepresentation);

    public abstract LikeRepresentation toTweetLikesRepresentation(Tweet tweet);

    public abstract LikeRepresentation toReplyLikesRepresentation(Reply reply);

    public abstract LikeRepresentation toRetweetLikesRepresentation(Retweet retweet);

    public abstract List<TweetRepresentation> toRepresentationList(List<Tweet> tweets);
}

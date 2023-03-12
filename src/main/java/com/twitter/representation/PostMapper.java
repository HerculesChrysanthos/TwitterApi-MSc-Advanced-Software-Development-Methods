package com.twitter.representation;

import com.twitter.domain.*;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = TweetBodyMapper.class)
public abstract class PostMapper {

    @Mapping(target = "content", source = "tweetBody")
    public abstract TweetRepresentation toTweetRepresentation(Tweet tweet);

    @Mapping(target = "parentPost", source = "parentPost")
    public abstract ReplyRepresentation toReplyRepresentation(Reply reply);
//    public abstract List<PostRepresentation> toRepresentationList(List<Post> posts);

    @Mapping(target = "parentPost", source = "originalPost")
    public abstract RetweetRepresentation toRetweetRepresentation(Retweet retweet);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateTime", ignore = true)
    @Mapping(target = "tweetBody", source = "content")
    public abstract Tweet toTweetModel(TweetRepresentation tweetRepresentation);
}

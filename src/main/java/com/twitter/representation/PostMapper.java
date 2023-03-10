//package com.twitter.representation;
//
//import com.twitter.domain.Post;
//import com.twitter.domain.Reply;
//import com.twitter.domain.Tweet;
//import com.twitter.domain.User;
//import org.mapstruct.InjectionStrategy;
//import org.mapstruct.Mapper;
//import org.mapstruct.Mapping;
//
//import java.util.List;
//
//@Mapper(componentModel = "cdi",
//        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
//        uses = {TweetBodyMapper.class})
//public abstract class PostMapper {
////    public abstract PostRepresentation toPostRepresentation(Post post);
//
//    @Mapping(target = "tweet.content", source = "content")
//    public abstract TweetRepresentation toTweetRepresentation(Tweet tweet);
//
//    public abstract ReplyRepresentation toReplyRepresentation(Reply reply);
////    public abstract List<PostRepresentation> toRepresentationList(List<Post> posts);
//}

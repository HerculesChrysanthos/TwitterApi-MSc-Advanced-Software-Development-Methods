package com.twitter.resource;

import com.twitter.TwitterException;
import com.twitter.domain.*;
import com.twitter.persistence.PostRepository;
import com.twitter.persistence.UserRepository;
import com.twitter.representation.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import java.net.URI;

import static com.twitter.resource.TwitterUri.POSTS;

@Path(POSTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PostResource {
    @Inject
    PostRepository postRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    PostMapper postMapper;

    @Inject
    UserMapper userMapper;

    @GET
    @Path("{postId:[0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response findPost(@PathParam("postId")Integer postId) {
        Post post = postRepository.findById(postId);

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DiscriminatorValue discriminatorValue = post.getClass().getAnnotation(DiscriminatorValue.class);
        switch (discriminatorValue.value()) {
            case "TWEET":
                Tweet tweet = (Tweet) post;
                return Response.ok().entity(postMapper.toTweetRepresentation(tweet)).build();
            case "REPLY":
                Reply reply = (Reply) post;
                return Response.ok().entity(postMapper.toReplyRepresentation(reply)).build();
            case "RETWEET":
                Retweet retweet = (Retweet) post;
            return Response.ok().entity(postMapper.toRetweetRepresentation(retweet)).build();
                default:
                // handle other cases
                break;
        }

        return Response.ok().build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createTweet(TweetRepresentation tweetRepresentation) {
        try {
            Tweet tweet = postMapper.toTweetModel(tweetRepresentation);

            if (tweet.getTweetBody().getTweetContent() == null) {
                throw new TwitterException("Max supported chars: 50");
            }

            postRepository.persist(tweet);

            URI uri = UriBuilder.fromResource(PostResource.class).path(String.valueOf(tweet.getId())).build();
            return Response.created(uri).entity(postMapper.toTweetRepresentation(tweet)).build();
        } catch (TwitterException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @POST
    @Path("{postId:[0-9]*}/reply")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createReply(
            @PathParam("postId") Integer postId,
            ReplyRepresentation replyRepresentation
    ) {
        Post post = postRepository.findById(postId);

        if (post == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Reply reply = postMapper.toReplyModel(replyRepresentation);
        reply.setParentPost(post);
        postRepository.persist(reply);

        URI uri = UriBuilder.fromResource(PostResource.class).path(String.valueOf(reply.getId())).build();
        return Response.created(uri).entity(postMapper.toReplyRepresentation(reply)).build();
    }

    @POST
    @Path("{postId:[0-9]*}/retweet")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createRetweet(
            @PathParam("postId") Integer postId,
            RetweetRepresentation retweetRepresentation
    ) {
        Post post = postRepository.findById(postId);

        if (post == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Retweet retweet = postMapper.toRetweetModel(retweetRepresentation);
        retweet.setOriginalPost(post);
        postRepository.persist(retweet);

        URI uri = UriBuilder.fromResource(PostResource.class).path(String.valueOf(retweet.getId())).build();
        return Response.created(uri).entity(postMapper.toRetweetRepresentation(retweet)).build();
    }

    @GET
    @Path("{postId:[0-9]*}/likes")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response postLikes(@PathParam("postId")Integer postId) {
        Post post = postRepository.findById(postId);

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        DiscriminatorValue discriminatorValue = post.getClass().getAnnotation(DiscriminatorValue.class);
        switch (discriminatorValue.value()) {
            case "TWEET":
                Tweet tweet = (Tweet) post;
                return Response.ok().entity(postMapper.toTweetLikesRepresentation(tweet)).build();
            case "REPLY":
                Reply reply = (Reply) post;
                return Response.ok().entity(postMapper.toReplyLikesRepresentation(reply)).build();
            case "RETWEET":
                Retweet retweet = (Retweet) post;
                return Response.ok().entity(postMapper.toRetweetLikesRepresentation(retweet)).build();
            default:
                // handle other cases
                break;
        }
        return Response.ok().build();
    }

    @PUT
    @Path("{postId:[0-9]*}/likes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response like(@HeaderParam("userId")Integer userId, @PathParam("postId")Integer postId) {
        User user = userRepository.findById(userId);
        Post post = postRepository.findById(postId);

        if (user == null || post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        post.addLike(user);

        return Response.noContent().build();
    }

    @DELETE
    @Path("{postId:[0-9]*}/likes")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response unlike(@HeaderParam("userId")Integer userId, @PathParam("postId")Integer postId) {
        User user = userRepository.findById(userId);
        Post post = postRepository.findById(postId);

        if (user == null || post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if(!post.removeLike(user)){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("{postId:[0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deletePost(@PathParam("postId")Integer postId) {
        Post post = postRepository.findById(postId);

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        postRepository.deleteById(postId);

        return Response.ok().build();
    }
}

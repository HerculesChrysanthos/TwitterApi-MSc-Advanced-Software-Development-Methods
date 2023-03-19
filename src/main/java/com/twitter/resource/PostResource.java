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
import java.util.List;

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

        if(post != null) {
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
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createTweet(@HeaderParam("userId")Integer userId, TweetRepresentation tweetRepresentation) {
        try {
            User user = userRepository.findById(userId);
            if(user == null) {
                return  Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse()).build();
            }

            tweetRepresentation.user = userMapper.toRepresentation(user);

            Tweet tweet = postMapper.toTweetModel(tweetRepresentation);

            if (tweet.getTweetBody().getTweetContent() == null) {
                throw new TwitterException("Max supported chars: 50");
            }

            postRepository.persist(tweet);

            URI uri = UriBuilder.fromResource(PostResource.class).path(String.valueOf(tweet.getId())).build();
            return Response.created(uri).entity(postMapper.toTweetRepresentation(tweet)).build();
        } catch (TwitterException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @POST
    @Path("{postId:[0-9]*}/reply")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createReply(
            @HeaderParam("userId")Integer userId,
            @PathParam("postId") Integer postId,
            ReplyRepresentation replyRepresentation
    ) {
        try {
            User user = userRepository.findById(userId);
            if(user == null) {
                return  Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse()).build();
            }

            replyRepresentation.user = userMapper.toRepresentation(user);

            Post post = postRepository.findById(postId);

            if (post == null){
                return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse()).build();
            }

            Reply reply = postMapper.toReplyModel(replyRepresentation);

            if (reply.getContent().getTweetContent() == null) {
                throw new TwitterException("Max supported chars: 50");
            }

            reply.setParentPost(post);
            postRepository.persist(reply);

            URI uri = UriBuilder.fromResource(PostResource.class).path(String.valueOf(reply.getId())).build();
            return Response.created(uri).entity(postMapper.toReplyRepresentation(reply)).build();
        } catch (TwitterException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new ErrorResponse(e.getMessage())).build();
        }
    }

    @POST
    @Path("{postId:[0-9]*}/retweet")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createRetweet(
            @HeaderParam("userId")Integer userId,
            @PathParam("postId") Integer postId,
            RetweetRepresentation retweetRepresentation
    ) {
        User user = userRepository.findById(userId);
        if(user == null) {
            return  Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse()).build();
        }

        retweetRepresentation.user = userMapper.toRepresentation(user);

        Post post = postRepository.findById(postId);

        if (post == null){
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse()).build();
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

        if(post != null) {
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
            }
        }
        return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse()).build();
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
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse("User '" + user.getUsername() + "' does not like given post.")).build();
        }

        return Response.noContent().build();
    }

    @DELETE
    @Path("{postId:[0-9]*}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response deletePost(@HeaderParam("userId")Integer userId, @PathParam("postId")Integer postId) {
        if(userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorResponse("Provide userId.")).build();
        }

        User user = userRepository.findById(userId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse("User not found.")).build();
        }

        Post post = postRepository.findById(postId);

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse("Post not found.")).build();
        }

        if(!post.getUser().getId().equals(user.getId())) {
            return Response.status(Response.Status.FORBIDDEN).entity(new ErrorResponse("")).build();
        }

        postRepository.deleteById(postId);

        return Response.ok().build();
    }

    @GET
    @Path("timeline")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response timeline(
            @HeaderParam("userId") Integer userId,
            @QueryParam("offset") Integer offset,
            @QueryParam("perPage") @DefaultValue("5") Integer perPage
    ) {
        if(userId == null) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(new ErrorResponse("Provide userId.")).build();
        }

        User user = userRepository.findById(userId);
        if(user == null) {
            return Response.status(Response.Status.NOT_FOUND).entity(new ErrorResponse("User not found.")).build();
        }

        Tweet tweet = offset != null ? (Tweet) postRepository.findById(offset) : null;

        List<Tweet> posts = postRepository.postsForUserTimeline(userId, tweet, perPage);
        return Response.ok().entity(postMapper.toRepresentationList(posts)).build();
    }
}

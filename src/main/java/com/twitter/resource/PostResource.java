package com.twitter.resource;

import com.twitter.domain.*;
import com.twitter.persistence.PostRepository;
import com.twitter.representation.TweetMapper;
//import com.twitter.representation.ReplyMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.DiscriminatorValue;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static com.twitter.resource.TwitterUri.POSTS;

@Path(POSTS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class PostResource {
    @Inject
    PostRepository postRepository;

    @Inject
    TweetMapper tweetMapper;
//    ReplyMapper replyMapper;
//    TweetMapper tweetMapper;

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
                return Response.ok().entity(tweetMapper.toRepresentation(tweet)).build();
            case "REPLY":
                Reply reply = (Reply) post;
//                return Response.ok().entity(replyMapper.toReplyRepresentation(reply)).build();
            case "RETWEET":
//                Retweet retweet = (Retweet) post;
//                return Response.ok().entity(postMapper.toRet)
                default:
                // handle other cases
                break;
        }

        return Response.ok().build();
    }

}

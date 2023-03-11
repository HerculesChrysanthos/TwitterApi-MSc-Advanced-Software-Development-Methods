package com.twitter.resource;

import com.twitter.TwitterException;
import com.twitter.domain.EmailAddress;
import com.twitter.domain.User;
import com.twitter.persistence.UserRepository;
import com.twitter.representation.UserMapper;
import com.twitter.representation.UserRepresentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.net.URI;
import java.util.List;
import java.util.Set;

import static com.twitter.resource.TwitterUri.USERS;

@Path(USERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    UserMapper userMapper;

    @Context
    UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public List<UserRepresentation> listAllUsers() {
        return userMapper.toRepresentationList(userRepository.listAll());
    }

    @GET
    @Path("{userId:[0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response find(@PathParam("userId")Integer userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().entity(userMapper.toRepresentation(user)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response create(UserRepresentation userRepresentation) {
        String username = userRepresentation.username;
        String email = userRepresentation.email;

        User existingUsername = userRepository.findByUsername(username);
        User existingEmail = userRepository.findByEmail(email);

        if(existingUsername != null) {
            return Response.status(Response.Status.CONFLICT).entity("User with username '" + username + "' already exists.").build();
        }

        if(existingEmail != null) {
            return Response.status(Response.Status.CONFLICT).entity("User with email '" + email + "' already exists.").build();
        }

        try{
            User user = userMapper.toModel(userRepresentation);

            if(user.getEmail().getEmail() == null){
                throw new TwitterException("Invalid email");
            }

            userRepository.persist(user);
            URI uri = UriBuilder.fromResource(UserResource.class).path(String.valueOf(user.getId())).build();
            return Response.created(uri).entity(userMapper.toRepresentation(user)).build();
        } catch(TwitterException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

    }


    @POST
    @Path("{userId1:[0-9]*}/follow/{userId2:[0-9]*}")
    @Transactional
    public Response follow(@PathParam("userId1")Integer userId1, @PathParam("userId2")Integer userId2) {
        User user1 = userRepository.findById(userId1);
        User user2 = userRepository.findById(userId2);
        if (user1.followUser(user2)) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @POST
    @Path("{userId1:[0-9]*}/unfollow/{userId2:[0-9]*}")
    @Transactional
    public Response unfollow(@PathParam("userId1")Integer userId1, @PathParam("userId2")Integer userId2) {
        User user1 = userRepository.findById(userId1);
        User user2 = userRepository.findById(userId2);
        if (user1.unfollowUser(user2)) {
            return Response.ok().build();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("{userId:[0-9]*}/following")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response following(@PathParam("userId")Integer userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Set<User> following = user.getFollowing();
        return Response.ok().entity(userMapper.toRepresentationSet(following)).build();
    }

}

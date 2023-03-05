package com.twitter.resource;

import com.twitter.domain.User;
import com.twitter.persistence.UserRepository;
import com.twitter.representation.UserMapper;
import com.twitter.representation.UserRepresentation;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Id;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.util.List;

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
    @Transactional
    public List<UserRepresentation> listAll() {
        return userMapper.toRepresentationList(userRepository.listAll());
    }
}

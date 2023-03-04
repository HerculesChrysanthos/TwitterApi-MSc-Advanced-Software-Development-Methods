package com.twitter.resource;

import com.twitter.domain.User;
import com.twitter.persistence.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import static com.twitter.resource.TwitterUri.USERS;

@Path(USERS)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class UserResource {

    @Inject
    UserRepository userRepository;

    @GET
    @Transactional
    public List<User> listAll() {
        return userRepository.listAll();
    }
}

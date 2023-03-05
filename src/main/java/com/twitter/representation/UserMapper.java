package com.twitter.representation;

import com.twitter.domain.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {

    @Mapping(target = "username")
//    @Mapping(target = "email.address")
    public abstract UserRepresentation toRepresentation(User user);

    @Mapping(target = "following", ignore = true)
    public abstract  User toModel(UserRepresentation representation);

    public abstract List<UserRepresentation> toRepresentationList(List<User> users);

}

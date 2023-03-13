package com.twitter.representation;

import com.twitter.domain.EmailAddress;
import com.twitter.domain.User;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class UserMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", source = "email.email")
    public abstract UserRepresentation toRepresentation(User user);

    @Mapping(target = "following", ignore = true)
    public abstract User toModel(UserRepresentation representation);

    @Mapping(target = "email")
    public abstract EmailAddress toEmailAddress(String email);

    public abstract List<UserRepresentation> toRepresentationList(List<User> users);

    public abstract List<UserBasicRepresentation> toFollowingRepresentationSet(Set<User> users);

    @Mapping(target = "username", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "dateOfBirth", ignore = true)
    @Mapping(target = "following", ignore = true)
    public abstract User toSimpleModel(UserRepresentation representation);
}

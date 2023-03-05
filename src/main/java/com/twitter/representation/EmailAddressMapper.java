package com.twitter.representation;

import com.twitter.domain.EmailAddress;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class EmailAddressMapper {

    public abstract EmailAddressRepresentation toRepresentation(EmailAddress emailAddress);

}

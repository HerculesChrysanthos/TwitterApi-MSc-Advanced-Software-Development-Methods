package com.twitter.representation;

import com.twitter.domain.DateOfBirth;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public abstract class DateOfBirthMapper {

    public abstract DateOfBirthRepresentation toRepresentation(DateOfBirth dateOfBirth);
    public abstract DateOfBirth toModel(DateOfBirthRepresentation representation);
}

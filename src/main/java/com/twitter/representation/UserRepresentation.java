package com.twitter.representation;

import com.twitter.domain.EmailAddress;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class UserRepresentation {
    public Integer id;
    public String username;
    public String password;
    public String email;
    public DateOfBirthRepresentation dateOfBirth;
}
package com.twitter.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class UserRepresentation {
    public Integer id;
    public String username;
    public String password;
    public EmailAddressRepresentation email;
    public DateOfBirthRepresentation dateOfBirth;
}
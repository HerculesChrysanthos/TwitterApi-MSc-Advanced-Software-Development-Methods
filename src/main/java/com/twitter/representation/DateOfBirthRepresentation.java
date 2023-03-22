package com.twitter.representation;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public class DateOfBirthRepresentation {
    public Integer day;
    public Integer month;
    public Integer year;

    public DateOfBirthRepresentation() { }
}

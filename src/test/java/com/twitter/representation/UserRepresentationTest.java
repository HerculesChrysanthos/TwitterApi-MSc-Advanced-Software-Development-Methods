package com.twitter.representation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserRepresentationTest {

    @Test
    public void testConstructorAndGetters() {
        Integer id = 1;
        String username = "nikolas";
        String password = "password123";
        String email = "nikolas@email.com";
        DateOfBirthRepresentation dob = new DateOfBirthRepresentation();
        dob.day = 1;
        dob.month = 1;
        dob.year = 1990;

        UserRepresentation user = new UserRepresentation();
        user.id = id;
        user.username = username;
        user.password = password;
        user.email = email;
        user.dateOfBirth = dob;

        Assertions.assertEquals(id, user.id);
        Assertions.assertEquals(username, user.username);
        Assertions.assertEquals(password, user.password);
        Assertions.assertEquals(email, user.email);
        Assertions.assertEquals(dob, user.dateOfBirth);
    }
}

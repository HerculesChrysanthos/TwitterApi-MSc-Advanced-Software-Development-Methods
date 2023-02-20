package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void UserConstructorAndGetters() {
        User user = new User(
                "nikolas", "1234",
                new DateOfBirth(1,1,2020),
                new EmailAddress("nl@gmail.com"));

        Assertions.assertEquals("nikolas", user.getUsername());
        Assertions.assertEquals("1234", user.getPassword());
        Assertions.assertEquals(new DateOfBirth(1,1,2020), user.getDateOfBirth());
        Assertions.assertEquals(new EmailAddress("nl@gmail.com"), user.getEmail());
    }

    @Test
    public void userSetters() {
        User user = new User();

        user.setUsername("nikolas");
        user.setPassword("1234");
        user.setEmail(new EmailAddress("nl@gmail.com"));
        user.setDateOfBirth(new DateOfBirth(1,1,2020));

        Assertions.assertEquals("nikolas", user.getUsername());
        Assertions.assertEquals("1234", user.getPassword());
        Assertions.assertEquals(new EmailAddress("nl@gmail.com"), user.getEmail());
        Assertions.assertEquals(new DateOfBirth(1,1,2020), user.getDateOfBirth());
    }
}

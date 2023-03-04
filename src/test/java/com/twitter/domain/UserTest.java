package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

    User user1;

    @BeforeEach
    public void setUp() {
        user1 = new User("user1", "1", new DateOfBirth(1,1,2001), new EmailAddress("email1@gmail.com"));
    }

    @Test
    public void testUserConstructorAndGetters() {
        User user1 = new User(
                "user1", "1",
                new DateOfBirth(1,1,2001),
                new EmailAddress("email1@gmail.com"));

        Assertions.assertEquals("user1", user1.getUsername());
        Assertions.assertEquals("1", user1.getPassword());
        Assertions.assertEquals(new DateOfBirth(1,1,2001), user1.getDateOfBirth());
        Assertions.assertEquals(new EmailAddress("email1@gmail.com"), user1.getEmail());
    }

    @Test
    public void testUserSetters() {
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

    @Test
    public void testGetFollowingUsers() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));
        User user4 = new User("user4","4",new DateOfBirth(1,7,2004),new EmailAddress("email4@gmail.com"));

        user1.setFollowing(user2);
        user2.setFollowing(user3);
        user2.setFollowing(user4);

        Assertions.assertTrue(user1.getFollowing().contains(user2));
        Assertions.assertTrue(user2.getFollowing().contains(user3));
        Assertions.assertTrue(user2.getFollowing().contains(user4));

        Assertions.assertEquals(0, user3.getFollowing().size());
        Assertions.assertEquals(1, user1.getFollowing().size());
        Assertions.assertEquals(2, user2.getFollowing().size());
        Assertions.assertFalse(user1.getFollowing().contains(user3));
    }

    @Test
    public void testUserCanNotFollowAFollowingUser() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        user1.setFollowing(user2);

        Assertions.assertFalse(user1.followUser(user2));
    }

    @Test
    public void testUserCanFollowANonFollowingUser() {
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Assertions.assertTrue(user1.followUser(user3));
    }

    @Test
    public void testUserCanNotFollowHimself() {
        Assertions.assertFalse(user1.followUser(user1));
    }
}

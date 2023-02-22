package com.twitter.domain;

import com.twitter.persistence.Initializer;
import com.twitter.persistence.JPAUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserTest {

    @BeforeEach
    public void setUp() {
        Initializer dataHelper = new Initializer();
        dataHelper.prepareData();
    }


//    @Test
//    public void UserConstructorAndGetters() {
//        User user = new User(
//                "nikolas", "1234",
//                new DateOfBirth(1,1,2020),
//                new EmailAddress("nl@gmail.com"));
//
//        Assertions.assertEquals("nikolas", user.getUsername());
//        Assertions.assertEquals("1234", user.getPassword());
//        Assertions.assertEquals(new DateOfBirth(1,1,2020), user.getDateOfBirth());
//        Assertions.assertEquals(new EmailAddress("nl@gmail.com"), user.getEmail());
//    }

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

    @Test
    public void getFollowingUsers() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> users = query.getResultList();

        Assertions.assertEquals(7, users.size());

        Assertions.assertEquals(0, users.get(0).getFollowing().size());

        Assertions.assertTrue(users.get(1).getFollowing().contains(users.get(2)));
        Assertions.assertTrue(users.get(2).getFollowing().contains(users.get(3)));
        Assertions.assertTrue(users.get(2).getFollowing().contains(users.get(4)));
        Assertions.assertTrue(users.get(2).getFollowing().contains(users.get(5)));

        Assertions.assertEquals(0, users.get(3).getFollowing().size());
        Assertions.assertEquals(0, users.get(4).getFollowing().size());
        Assertions.assertEquals(0, users.get(5).getFollowing().size());
        Assertions.assertEquals(0, users.get(6).getFollowing().size());

        Assertions.assertFalse(users.get(1).getFollowing().contains(users.get(3)));
    }

    @Test
    public void UserCanNotFollowAFollowingUser() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> users = query.getResultList();

        Assertions.assertFalse(users.get(1).followUser(users.get(2)));
    }
    @Test
    public void userCanFollowANonFollowingUser() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> users = query.getResultList();

        Assertions.assertTrue(users.get(0).followUser(users.get(1)));
    }

    @Test
    public void userCanNotFollowHimself() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> users = query.getResultList();

        Assertions.assertFalse(users.get(1).followUser(users.get(1)));
    }
}

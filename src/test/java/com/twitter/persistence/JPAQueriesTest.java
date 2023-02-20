package com.twitter.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.twitter.domain.Tweet;
import com.twitter.domain.User;
import org.junit.jupiter.api.*;

public class JPAQueriesTest {

    @BeforeEach
    public void setUp() {
        Initializer dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @AfterEach
    public void tearDown() { }

    @Test
    public void listTweets() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select tweet from Tweet tweet");
        List<Tweet> results = query.getResultList();

        Assertions.assertEquals(6,results.size());
    }

    @Test
    public void listUsers() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> results = query.getResultList();

        Assertions.assertEquals(7,results.size());
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
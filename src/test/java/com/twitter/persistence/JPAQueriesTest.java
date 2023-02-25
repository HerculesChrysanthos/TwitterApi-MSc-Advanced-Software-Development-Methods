package com.twitter.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.twitter.domain.Post;
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

        Assertions.assertEquals(3,results.size());
    }

    @Test
    public void listUsers() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> results = query.getResultList();

        Assertions.assertEquals(3,results.size());
    }

    @Test
    public void testGetFollowingUsers() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> users = query.getResultList();

        Assertions.assertEquals(1, users.get(0).getFollowing().size());

        Assertions.assertTrue(users.get(0).getFollowing().contains(users.get(1)));
        Assertions.assertTrue(users.get(1).getFollowing().contains(users.get(2)));

        Assertions.assertEquals(0, users.get(2).getFollowing().size());

        Assertions.assertFalse(users.get(0).getFollowing().contains(users.get(2)));
    }

    @Test
    public void testUserCanNotFollowAFollowingUser() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select user from User user");
        List<User> users = query.getResultList();

        Assertions.assertFalse(users.get(0).followUser(users.get(1)));
    }

    @Test
    public void testAddReplyToTweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> tweets = query.getResultList();

        Assertions.assertEquals( 1, tweets.get(0).getReplies().size());
        Assertions.assertEquals("This is a reply to tweet #1", tweets.get(0).getReplies().iterator().next().getContent().getTweetContent());
    }

    @Test
    public void testAddReplyToReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply");
        List<Post> replies = query.getResultList();

        Assertions.assertEquals( 3, replies.size());
        Assertions.assertEquals( 1, replies.get(0).getReplies().size());
        Assertions.assertEquals("This is a reply to reply #1", replies.get(0).getReplies().iterator().next().getContent().getTweetContent());
    }

    @Test
    public void testAddReplyToRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet");
        List<Post> retweets = query.getResultList();
        Assertions.assertEquals(3, retweets.size());
        Assertions.assertEquals(1, retweets.get(2).getReplies().size());

        Assertions.assertEquals("This is a reply to retweet #1", retweets.get(2).getReplies().iterator().next().getContent().getTweetContent());
    }

    }
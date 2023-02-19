package com.twitter;

import com.twitter.domain.Post;
import com.twitter.domain.User;
import com.twitter.persistence.Initializer;
import com.twitter.persistence.JPAUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class PostTest {
    @BeforeEach
    public void setUp() {
        Initializer dataHelper = new Initializer();
        dataHelper.prepareData();
    }

    @AfterEach
    public void tearDown() { }

    /**
     * Done
     */
    @Test
    public void addReplyToTweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> tweets = query.getResultList();
        Assertions.assertEquals( 1, tweets.get(0).getReplies().size());
        Assertions.assertEquals("This is a reply to tweet #1", tweets.get(0).getReplies().iterator().next().getContent().getTweetBody());
    }

    /**
     * Done
     */
    @Test
    public void addReplyToReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply");
        List<Post> replies = query.getResultList();
        Assertions.assertEquals( 2, replies.size());
        Assertions.assertEquals( 1, replies.get(0).getReplies().size());

        Assertions.assertEquals("This is a reply to reply #1", replies.get(0).getReplies().iterator().next().getContent().getTweetBody());
    }

    //TODO
    @Test
    public void addRetweetToTweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> posts = query.getResultList();
        Assertions.assertTrue(true);
    }

    //TODO
    @Test
    public void addReplyToRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> posts = query.getResultList();
    }
    //TODO
    @Test
    public void addRetweetToRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> posts = query.getResultList();
    }
    //TODO
    @Test
    public void addRetweetToReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> posts = query.getResultList();
    }

    @Test
    public void createRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet ");
        List<Post> tweets = query.getResultList();
        Assertions.assertEquals( 1, tweets.get(0).getReplies().size());
    }
    @Test
    public void userLikesTweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet ");
        List<Post> tweets = query.getResultList();
        Assertions.assertEquals( 2, tweets.get(0).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(tweets.get(0).getLikes().contains(users.get(1)));
        Assertions.assertTrue(tweets.get(0).getLikes().contains(users.get(6)));
        Assertions.assertFalse(tweets.get(0).getLikes().contains(users.get(2)));
    }
    @Test
    public void userLikesReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply ");
        List<Post> replies = query.getResultList();
        Assertions.assertEquals( 1, replies.get(0).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(replies.get(0).getLikes().contains(users.get(3)));
        Assertions.assertFalse(replies.get(0).getLikes().contains(users.get(6)));
    }

    //TODO
    public void userLikesRetweets() {

    }

    @Test
    public void userRemovesLikeTweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet ");
        List<Post> tweets = query.getResultList();
        Assertions.assertEquals( 2, tweets.get(0).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(tweets.get(0).getLikes().contains(users.get(1)));

        tweets.get(0).removeLike(users.get(1));

        Assertions.assertFalse(tweets.get(0).getLikes().contains(users.get(1)));
        Assertions.assertEquals( 1, tweets.get(0).getLikes().size());
    }
    @Test
    public void userRemovesLikeReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply ");
        List<Post> replies = query.getResultList();
        Assertions.assertEquals( 1, replies.get(0).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(replies.get(0).getLikes().contains(users.get(3)));

        replies.get(0).removeLike(users.get(3));

        Assertions.assertFalse(replies.get(0).getLikes().contains(users.get(3)));
        Assertions.assertEquals( 0, replies.get(0).getLikes().size());
    }

    //TODO
    public void userRemovesLikeRetweets() {

    }
}

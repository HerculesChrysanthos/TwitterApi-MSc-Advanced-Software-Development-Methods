package com.twitter.domain;

import com.twitter.domain.Post;
import com.twitter.domain.User;
import com.twitter.persistence.Initializer;
import com.twitter.persistence.JPAUtil;
import org.junit.jupiter.api.*;

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
    public void testAddReplyToTweet() {
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
    public void testAddReplyToReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply");
        List<Post> replies = query.getResultList();
        Assertions.assertEquals( 3, replies.size());
        Assertions.assertEquals( 1, replies.get(0).getReplies().size());

        Assertions.assertEquals("This is a reply to reply #1", replies.get(0).getReplies().iterator().next().getContent().getTweetBody());
    }

    /**
     * Done
     */
    @Test
    public void testAddRetweetToTweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Tweet");
        List<Post> tweets = query.getResultList();
        Assertions.assertEquals( 1, tweets.get(0).getRetweets().size());
    }

    /**
     * Done
     */
    @Test
    public void testAddReplyToRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet");
        List<Post> retweets = query.getResultList();
        Assertions.assertEquals( 3, retweets.size());
        Assertions.assertEquals( 1, retweets.get(2).getReplies().size());

        Assertions.assertEquals("This is a reply to retweet #1", retweets.get(2).getReplies().iterator().next().getContent().getTweetBody());
    }

    /**
     * Done
     */
    @Test
    public void testAddRetweetToReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply");
        List<Post> replies = query.getResultList();

        Assertions.assertEquals( 1, replies.get(0).getRetweets().size());
    }

    /**
     * Done
     */
    @Test
    public void testAddRetweetToRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet");
        List<Post> retweets = query.getResultList();
        Assertions.assertEquals( 3, retweets.size());
        Assertions.assertEquals( 1, retweets.get(0).getRetweets().size());
    }

    /**
     * Done
     */
    @Test
    public void testCreateRetweet() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet ");
        List<Post> tweets = query.getResultList();

        Assertions.assertEquals( 3, tweets.size());
    }

    /**
     * Done
     */
    @Test
    public void testUserLikesTweet() {
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

    /**
     * Done
     */
    @Test
    public void testUserLikesReply() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Reply");
        List<Post> replies = query.getResultList();
        Assertions.assertEquals( 1, replies.get(0).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(replies.get(0).getLikes().contains(users.get(3)));
        Assertions.assertFalse(replies.get(0).getLikes().contains(users.get(6)));
    }

    /**
     * Done
     */
    @Test
    public void testUserLikesRetweets() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet");
        List<Post> retweets = query.getResultList();
        Assertions.assertEquals( 1, retweets.get(2).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(retweets.get(2).getLikes().contains(users.get(4)));
        Assertions.assertFalse(retweets.get(2).getLikes().contains(users.get(6)));
    }

    /**
     * Done
     */
    @Test
    public void testUserRemovesLikeTweet() {
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

    /**
     * Done
     */
    @Test
    public void testUserRemovesLikeReply() {
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

    /**
     * Done
     */
    @Test
    public void testUserRemovesLikeRetweets() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        Query query = em.createQuery("select post from Post post where TYPE(post) = Retweet");
        List<Post> retweets = query.getResultList();
        Assertions.assertEquals( 1, retweets.get(2).getLikes().size());

        Query query2 = em.createQuery("select user from User user");
        List<User> users = query2.getResultList();
        Assertions.assertTrue(retweets.get(2).getLikes().contains(users.get(4)));

        retweets.get(2).removeLike(users.get(4));

        Assertions.assertFalse(retweets.get(2).getLikes().contains(users.get(3)));
        Assertions.assertEquals( 0, retweets.get(2).getLikes().size());
    }
}

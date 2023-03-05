package com.twitter.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.twitter.domain.*;

public class Initializer  {

    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("delete from following_users").executeUpdate();
        em.createNativeQuery("delete from user_posts").executeUpdate();
        em.createNativeQuery("delete from posts_posts").executeUpdate();
        em.createNativeQuery("delete from posts").executeUpdate();
        em.createNativeQuery("delete from users").executeUpdate();

        tx.commit();
        em.close();
    }

    public void prepareData() {
        eraseData();

        EntityManager em = JPAUtil.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        User user1 = new User("user1", "1", new DateOfBirth(1,1,2001), new EmailAddress("email1@gmail.com"));
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Tweet tweet1 = new Tweet(new TweetBody("This is tweet #1"));
        Tweet tweet2 = new Tweet(new TweetBody("This is tweet #2"));
        Tweet tweet3 = new Tweet(new TweetBody("This is tweet #3"));

        tweet1.setUser(user1);
        tweet2.setUser(user2);
        tweet3.setUser(user2);

        user1.setFollowing(user2);
        user2.setFollowing(user3);

        // Reply to Tweet
        tweet1.addReply(user1, new TweetBody("This is a reply to tweet #1"));

        // Reply to Reply
        tweet1.getReplies().iterator().next().addReply(user3, new TweetBody("This is a reply to reply #1"));

        // Retweet to Tweet
        tweet1.addRetweet(user3);

        // Retweet to Reply
        tweet2.addReply(user1, new TweetBody("This is a reply to tweet #2"));
        tweet2.getReplies().iterator().next().addRetweet(user3);

        // Reply to Retweet
        tweet3.addRetweet(user2);
        tweet3.getRetweets().iterator().next().addReply(user1, new TweetBody("This is a reply to retweet #3"));

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);

        em.persist(tweet1);
        em.persist(tweet2);
        em.persist(tweet3);

        tx.commit();
        em.close();
    }
}

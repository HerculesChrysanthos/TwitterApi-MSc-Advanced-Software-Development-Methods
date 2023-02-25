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

        EntityManager em = JPAUtil.getCurrentEntityManager();
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
        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        // Reply to Reply
        Reply reply2 = new Reply(user3, reply1, new TweetBody("This is a reply to reply #1"));
        reply1.addReply(reply2);

        // Retweet to Tweet
        Retweet retweet1 = new Retweet(user3);
        tweet1.addRetweet(retweet1);

        // Retweet to Reply
        Retweet retweet2 = new Retweet(user3);
        reply1.addRetweet(retweet2);

        Retweet retweet3 = new Retweet(user3);

        Reply reply3 = new Reply(user3, retweet3, new TweetBody("This is a reply to retweet #1"));
        retweet3.addReply(reply3);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);

        em.persist(tweet1);
        em.persist(tweet2);
        em.persist(tweet3);

        em.persist(reply3);

        tx.commit();
        em.close();
    }
}

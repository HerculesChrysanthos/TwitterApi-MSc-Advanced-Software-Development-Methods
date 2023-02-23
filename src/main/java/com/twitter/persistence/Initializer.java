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

        User user0 = new User("user0", "0", new DateOfBirth(1,1,2000), new EmailAddress("email0@gmail.com"));
        User user1 = new User("user1", "1", new DateOfBirth(1,1,2001), new EmailAddress("email1@gmail.com"));
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));
        User user4 = new User("user4","4",new DateOfBirth(1,7,2004),new EmailAddress("email4@gmail.com"));
        User user5 = new User("user5","5",new DateOfBirth(1,7,2005),new EmailAddress("email5@gmail.com"));
        User user6 = new User("user6","6",new DateOfBirth(1,7,2006),new EmailAddress("email6@gmail.com"));

        Tweet tweet1 = new Tweet(new TweetBody("This is tweet #1"));
        Tweet tweet2 = new Tweet(new TweetBody("This is tweet #2"));
        Tweet tweet3 = new Tweet(new TweetBody("This is tweet #3"));
        Tweet tweet4 = new Tweet(new TweetBody("This is tweet #4"));
        Tweet tweet5 = new Tweet(new TweetBody("This is tweet #5"));
        Tweet tweet6 = new Tweet(new TweetBody("This is tweet #6"));

        tweet1.setUser(user1);
        tweet2.setUser(user2);
        tweet3.setUser(user2);
        tweet4.setUser(user2);
        tweet5.setUser(user3);
        tweet6.setUser(user4);

        user1.setFollowing(user2);
        user2.setFollowing(user3);
        user2.setFollowing(user4);
        user2.setFollowing(user5);

        // Reply to Tweet
        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        // Reply to Reply
        Reply reply2 = new Reply(user4, reply1, new TweetBody("This is a reply to reply #1"));
        reply1.addReply(reply2);

        tweet1.addLike(user1);
        tweet1.addLike(user6);

        reply1.addLike(user3);

        // Retweet to Tweet
        Retweet retweet1 = new Retweet(user5);
        tweet1.addRetweet(retweet1);

        // Reply to Retweet
        Reply reply3 = new Reply(user6, retweet1, new TweetBody("This is a reply to retweet #1"));
        retweet1.addReply(reply3);

        // Retweet to Reply
        Retweet retweet2 = new Retweet(user4);
        reply1.addRetweet(retweet2);

        // Retweet to Retweet
        Retweet retweet3 = new Retweet(user6);
        retweet2.addRetweet(retweet3);

        // Like a Retweet
        retweet1.addLike(user4);

        em.persist(user0);
        em.persist(user1);
        em.persist(user2);
        em.persist(user3);
 //       em.persist(user4);
        em.persist(user5);
//        em.persist(user6);

        em.persist(tweet1);
        em.persist(tweet2);
        em.persist(tweet3);
        em.persist(tweet4);
        em.persist(tweet5);
        em.persist(tweet6);

        em.persist(reply1);
        em.persist(reply2);
//        em.persist(reply3);

        em.persist(retweet1);
        em.persist(retweet2);
//        em.persist(retweet3);

        tx.commit();
        em.close();
    }
}

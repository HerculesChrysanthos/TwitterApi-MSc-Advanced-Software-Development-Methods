//package com.twitter.persistence;
//
//import com.twitter.domain.*;
//
//import javax.persistence.EntityManager;
//import javax.persistence.EntityTransaction;
//import java.time.LocalDateTime;
//
//public class InitializerService {
//
//    public void  eraseData() {
//        EntityManager em = JPAUtil.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        em.createNativeQuery("delete from following_users").executeUpdate();
//        em.createNativeQuery("delete from user_posts").executeUpdate();
//        em.createNativeQuery("delete from posts_posts").executeUpdate();
//        em.createNativeQuery("delete from posts").executeUpdate();
//        em.createNativeQuery("delete from users").executeUpdate();
//
//        tx.commit();
//        em.close();
//    }
//
//    public void prepareData() {
//        eraseData();
//
//        EntityManager em = JPAUtil.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        User user1 = new User("user1", "1", new DateOfBirth(1,1,2001), new EmailAddress("email1@gmail.com"));
//        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
//        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));
//        User user4 = new User("user4","4",new DateOfBirth(1,7,2004),new EmailAddress("email4@gmail.com"));
//
//        Tweet tweet1 = new Tweet(new TweetBody("This is tweet #1"));
//        tweet1.setDateTime(LocalDateTime.of(2023, 2, 25, 10, 10, 10));
//        Tweet tweet2 = new Tweet(new TweetBody("This is tweet #2"));
//        tweet2.setDateTime(LocalDateTime.of(2023, 2, 25, 13, 10, 10));
//        Tweet tweet3 = new Tweet(new TweetBody("This is tweet #3"));
//        tweet3.setDateTime(LocalDateTime.of(2023, 2, 25, 16, 10, 10));
//        Tweet tweet4 = new Tweet(new TweetBody("This is tweet #4"));
//        tweet4.setDateTime(LocalDateTime.of(2023, 2, 25, 19, 10, 10));
//
//        Tweet tweet5 = new Tweet(new TweetBody("This is tweet #5"));
//        tweet5.setDateTime(LocalDateTime.of(2023, 2, 25, 11, 10, 10));
//        Tweet tweet6 = new Tweet(new TweetBody("This is tweet #6"));
//        tweet6.setDateTime(LocalDateTime.of(2023, 2, 25, 14, 10, 10));
//        Tweet tweet7 = new Tweet(new TweetBody("This is tweet #7"));
//        tweet7.setDateTime(LocalDateTime.of(2023, 2, 25, 17, 10, 10));
//        Tweet tweet8 = new Tweet(new TweetBody("This is tweet #8"));
//        tweet8.setDateTime(LocalDateTime.of(2023, 2, 25, 20, 10, 10));
//
//        Tweet tweet9 = new Tweet(new TweetBody("This is tweet #9"));
//        tweet9.setDateTime(LocalDateTime.of(2023, 2, 25, 12, 10, 10));
//        Tweet tweet10 = new Tweet(new TweetBody("This is tweet #10"));
//        tweet10.setDateTime(LocalDateTime.of(2023, 2, 25, 15, 10, 10));
//        Tweet tweet11 = new Tweet(new TweetBody("This is tweet #11"));
//        tweet11.setDateTime(LocalDateTime.of(2023, 2, 25, 18, 10, 10));
//        Tweet tweet12 = new Tweet(new TweetBody("This is tweet #12"));
//        tweet12.setDateTime(LocalDateTime.of(2023, 2, 25, 21, 10, 10));
//
//
//        tweet1.setUser(user2);
//        tweet2.setUser(user2);
//        tweet3.setUser(user2);
//        tweet4.setUser(user2);
//
//        tweet5.setUser(user3);
//        tweet6.setUser(user3);
//        tweet7.setUser(user3);
//        tweet8.setUser(user3);
//
//        tweet9.setUser(user4);
//        tweet10.setUser(user4);
//        tweet11.setUser(user4);
//        tweet12.setUser(user4);
//
//        user1.setFollowing(user2);
//        user1.setFollowing(user3);
//        user1.setFollowing(user4);
//
//        em.persist(user1);
//
//
//        em.persist(tweet1);
//        em.persist(tweet2);
//        em.persist(tweet3);
//        em.persist(tweet4);
//        em.persist(tweet5);
//        em.persist(tweet6);
//        em.persist(tweet7);
//        em.persist(tweet8);
//        em.persist(tweet9);
//        em.persist(tweet10);
//        em.persist(tweet11);
//        em.persist(tweet12);
//
//        tx.commit();
//        em.close();
//    }
//}

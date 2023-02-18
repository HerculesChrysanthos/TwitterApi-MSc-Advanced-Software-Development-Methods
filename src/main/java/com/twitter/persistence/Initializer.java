package com.twitter.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.twitter.domain.*;

public class Initializer  {

    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

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

        User user1 = new User("herculesCh","1234",new DateOfBirth(1,7,1999),new EmailAddress("h@gmail.com"));
        Tweet tweet1 = new Tweet("kalispera kai kali vradia");

        User user2 = new User("nikolaslepidas","1234",new DateOfBirth(1,7,1999),new EmailAddress("nl@gmail.com"));

        User user3 = new User("mikeathanasopoulos","1234",new DateOfBirth(1,7,1999),new EmailAddress("mike@gmail.com"));

        user1.setFollowing(user2);
        user1.setFollowing(user3);

        tweet1.setUser(user1);

        em.persist(user1);
        em.persist(user2);
        em.persist(user3);

        em.persist(tweet1);

        tx.commit();
        em.close();
    }
}

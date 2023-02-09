package com.twitter.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import com.twitter.customTypes.EmailAddress;
import com.twitter.domain.Tweet;
import com.twitter.domain.User;

public class Initializer  {

    public void  eraseData() {
        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.createNativeQuery("delete from replies").executeUpdate();
        em.createNativeQuery("delete from tweets").executeUpdate();
        em.createNativeQuery("delete from users").executeUpdate();

        tx.commit();
        em.close();
    }

    public void prepareData() {
        eraseData();

        EntityManager em = JPAUtil.getCurrentEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        User user1 = new User("nikolaslepidas", "1234", "nl@gmail.com");
        Tweet tweet1 = new Tweet("My first tweet");
        tweet1.setUser(user1);

        em.persist(user1);
        em.persist(tweet1);

        tx.commit();
        em.close();
    }
}

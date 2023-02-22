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


}
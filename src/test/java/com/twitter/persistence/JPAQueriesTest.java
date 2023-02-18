package com.twitter.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.twitter.domain.Tweet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.twitter.persistence.Initializer;
import com.twitter.persistence.JPAUtil;

public class JPAQueriesTest {
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        Initializer dataHelper = new Initializer();
        dataHelper.prepareData();

        em = JPAUtil.getCurrentEntityManager();
    }

    @AfterEach
    public void tearDown() {
        em.close();
    }

    @Test
    public void listTweets() {
        Query query = em.createQuery("select tweet from Tweet tweet");
        List<Tweet> results = query.getResultList();

        Assertions.assertEquals(1,results.size());
    }
}
package com.twitter.Domain;

import com.twitter.domain.Tweet;
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
        System.out.println(results);
        Tweet tweet = results.get(0);

        Assertions.assertEquals("kalispera kai kali vradia", tweet.getTweetBody());
        Assertions.assertEquals("herculesCh", tweet.getUser().getUsername());

//        Assertions.assertEquals(tweet.getLikeCount(), 0);
//        Assertions.assertEquals(tweet.getRetweetCount(), 0);
//        Assertions.assertEquals(tweet.getTweetBody(), "My first tweet");
//        Assertions.assertEquals(tweet.getUser().getUsername(), "nikolaslepidas");
//        Assertions.assertEquals(tweet.getUser().getPassword(), "1234");
//        Assertions.assertEquals(tweet.getUser().getEmail(), "nl@gmail.com");
    }
}

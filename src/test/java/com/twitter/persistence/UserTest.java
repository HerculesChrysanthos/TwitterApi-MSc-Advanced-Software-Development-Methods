package com.twitter.persistence;

import com.twitter.domain.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class UserTest {
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
    public void listUsers() {
        Query usersQuery = em.createQuery("select user from User user");
        List<User> results = usersQuery.getResultList();
        System.out.println(results.toString());
        Assertions.assertEquals(3,results.size());

        Assertions.assertTrue(results.get(0).getFollowing().contains(results.get(2)));
        Assertions.assertFalse(results.get(2).getFollowing().contains(results.get(0)));
    }

}

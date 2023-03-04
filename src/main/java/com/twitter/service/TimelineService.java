package com.twitter.service;

import com.twitter.domain.Post;
import com.twitter.domain.Tweet;
import com.twitter.domain.User;
import javafx.animation.Timeline;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Set;

public class TimelineService {

    private EntityManager em;

    public TimelineService(EntityManager em) {this.em = em;}

    public List<Tweet> getTimeline(User user){
        Query query = em.createQuery("SELECT p FROM Post p WHERE TYPE(p) = Tweet and p.user IN (SELECT f FROM User u JOIN u.following f WHERE u = :user) ORDER BY p.dateTime DESC", Post.class);
        query.setParameter("user", user);
        query.setMaxResults(3);
        List<Tweet> top4TweetFromFollowing = query.getResultList();

        return top4TweetFromFollowing;
    }
}

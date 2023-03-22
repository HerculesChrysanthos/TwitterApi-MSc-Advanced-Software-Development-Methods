//package com.twitter.service;
//
//import com.twitter.domain.Post;
//import com.twitter.domain.Tweet;
//import com.twitter.domain.User;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//import javax.persistence.Query;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//public class TimelineServiceTest extends TwitterServiceTest {
//
//
//    @Override
//    protected void afterTearDown() {
//    }
//
//    @SuppressWarnings("unchecked")
//    @Test
//    public void testGetTimelineJPA() {
//
//        Query query = em.createQuery("select user from User user");
//        List<User> users = query.getResultList();
//
//        TimelineService timelineService = new TimelineService(em);
//
//        List<Tweet> myTimeline = timelineService.getTimeline(users.get(0));
//        Assertions.assertEquals("This is tweet #12", myTimeline.get(0).getTweetBody().getTweetContent());
//        Assertions.assertEquals("This is tweet #8", myTimeline.get(1).getTweetBody().getTweetContent());
//        Assertions.assertEquals("This is tweet #4", myTimeline.get(2).getTweetBody().getTweetContent());
//    }
//
//}

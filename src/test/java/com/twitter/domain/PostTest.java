package com.twitter.domain;

import com.twitter.util.SystemDateTime;
import com.twitter.util.SystemDateTimeStub;
import org.junit.jupiter.api.*;
import java.time.LocalDateTime;

public class PostTest {
    Tweet tweet1;
    User user1;
    @BeforeEach
    public void setUp() {
        tweet1 = new Tweet();
        tweet1.setTweetBody(new TweetBody("This is tweet #1"));

        user1 = new User("user1", "0", new DateOfBirth(1,1,2001), new EmailAddress("email1@gmail.com"));
        tweet1.setUser(user1);
    }

    @AfterEach
    public void tearDown() { }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddReplyToTweet() {
        tweet1.addReply(user1, new TweetBody("This is a reply to tweet #1"));

        Assertions.assertEquals( 1, tweet1.getReplies().size());
        Assertions.assertEquals("This is a reply to tweet #1", tweet1.getReplies().iterator().next().getContent().getTweetContent());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddReplyToReply() {
        tweet1.addReply(user1, new TweetBody("This is a reply to tweet #1"));

        tweet1.getReplies().iterator().next().addReply(user1, new TweetBody("This is a reply to reply #1"));

        Assertions.assertEquals( 1, tweet1.getReplies().size());
        Assertions.assertEquals( 1, tweet1.getReplies().iterator().next().getReplies().size());
        Assertions.assertEquals("This is a reply to reply #1", tweet1.getReplies().iterator().next().getReplies().iterator().next().getContent().getTweetContent());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddRetweetToTweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        tweet1.addRetweet(user2);

        Assertions.assertEquals( 1, tweet1.getRetweets().size());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddReplyToRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        tweet1.addRetweet(user2);

        tweet1.getRetweets().iterator().next().addReply(user1, new TweetBody("This is a reply to retweet #1"));

        Assertions.assertEquals( 1, tweet1.getRetweets().size());
        Assertions.assertEquals( 1, tweet1.getRetweets().iterator().next().getReplies().size());
        Assertions.assertEquals("This is a reply to retweet #1", tweet1.getRetweets().iterator().next().getReplies().iterator().next().getContent().getTweetContent());
    }


    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddRetweetToReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        tweet1.addReply(user1, new TweetBody("This is a reply to tweet #1"));

        tweet1.getReplies().iterator().next().addRetweet(user2);
        Assertions.assertEquals( 1, tweet1.getReplies().iterator().next().getRetweets().size());

    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddRetweetToRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addRetweet(user2);

        tweet1.getRetweets().iterator().next().addRetweet(user3);

        Assertions.assertEquals( 1, tweet1.getRetweets().size());
        Assertions.assertEquals( 1, tweet1.getRetweets().iterator().next().getRetweets().size());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testCreateRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addRetweet(user2);
        tweet1.addRetweet(user3);

        Assertions.assertEquals( 2, tweet1.getRetweets().size());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testUserLikesTweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addLike(user1);
        tweet1.addLike(user2);
        tweet1.addLike(user3);

        Assertions.assertEquals(3, tweet1.getLikes().size());
        Assertions.assertTrue(tweet1.getLikes().contains(user1));
        Assertions.assertTrue(tweet1.getLikes().contains(user2));
        Assertions.assertTrue(tweet1.getLikes().contains(user3));
    }

    @Test
    public void testUserCanNotLikeAlreadyLikedTweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        tweet1.addLike(user2);

        Assertions.assertFalse(tweet1.addLike(user2));
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testUserLikesReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addLike(user1);
        tweet1.addLike(user2);

        tweet1.addReply(user1, new TweetBody("This is a reply to tweet #1"));

        tweet1.getReplies().iterator().next().addLike(user3);

        Assertions.assertEquals(1, tweet1.getReplies().iterator().next().getLikes().size());
        Assertions.assertTrue(tweet1.getReplies().iterator().next().getLikes().contains(user3));
        Assertions.assertFalse(tweet1.getReplies().iterator().next().getLikes().contains(user2));
    }

    @Test
    public void testUserCanNotLikeAlreadyLikedReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Reply reply1 = new Reply(user2, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(user2, new TweetBody("This is a reply to tweet #1"));

        reply1.addLike(user3);

        Assertions.assertFalse(reply1.addLike(user3));
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testUserLikesRetweets() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addLike(user1);
        tweet1.addLike(user2);

        tweet1.addRetweet(user3);

        tweet1.getRetweets().iterator().next().addLike(user3);

        Assertions.assertEquals(1, tweet1.getRetweets().iterator().next().getLikes().size());
        Assertions.assertTrue(tweet1.getRetweets().iterator().next().getLikes().contains(user3));
        Assertions.assertFalse(tweet1.getRetweets().iterator().next().getLikes().contains(user2));

    }

    @Test
    public void testUserCanNotLikeAlreadyLikedRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addRetweet(user2);

        tweet1.getRetweets().iterator().next().addLike(user3);

        Assertions.assertFalse(tweet1.getRetweets().iterator().next().addLike(user3));
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testUserRemovesLikeTweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addLike(user1);
        tweet1.addLike(user2);
        tweet1.addLike(user3);

        Assertions.assertEquals(3, tweet1.getLikes().size());

        tweet1.removeLike(user1);

        Assertions.assertEquals(2, tweet1.getLikes().size());

        Assertions.assertFalse(tweet1.getLikes().contains(user1));
        Assertions.assertTrue(tweet1.getLikes().contains(user2));
        Assertions.assertTrue(tweet1.getLikes().contains(user3));
    }

    @Test
    public void testUserCanNotRemoveAlreadyRemovedLikeFromTweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        tweet1.addLike(user2);
        tweet1.removeLike(user2);

        Assertions.assertFalse(tweet1.removeLike(user2));
    }


    /**
     * Done with JUnit tests
     */
    @Test
    public void testUserRemovesLikeReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addLike(user1);
        tweet1.addLike(user2);
        tweet1.addLike(user3);

        tweet1.addReply(user1, new TweetBody("This is a reply to tweet #1"));

        tweet1.getReplies().iterator().next().addLike(user2);
        tweet1.getReplies().iterator().next().addLike(user3);

        Assertions.assertEquals(2, tweet1.getReplies().iterator().next().getLikes().size());

        tweet1.getReplies().iterator().next().removeLike(user2);

        Assertions.assertEquals(1, tweet1.getReplies().iterator().next().getLikes().size());

        Assertions.assertFalse(tweet1.getReplies().iterator().next().getLikes().contains(user1));
        Assertions.assertFalse(tweet1.getReplies().iterator().next().getLikes().contains(user2));
        Assertions.assertTrue(tweet1.getReplies().iterator().next().getLikes().contains(user3));
    }

    @Test
    public void testUserCanNotRemoveAlreadyRemovedLikeFromReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addReply(user2, new TweetBody("This is a reply to tweet #1"));

        tweet1.setUser(user1);
        tweet1.getReplies().iterator().next().addLike(user3);
        tweet1.getReplies().iterator().next().removeLike(user3);

        Assertions.assertFalse(tweet1.getReplies().iterator().next().removeLike(user3));
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testUserRemovesLikeRetweets() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addLike(user1);
        tweet1.addLike(user2);
        tweet1.addLike(user3);

        tweet1.addRetweet(user3);

        tweet1.getRetweets().iterator().next().addLike(user1);
        tweet1.getRetweets().iterator().next().addLike(user2);

        Assertions.assertEquals(2, tweet1.getRetweets().iterator().next().getLikes().size());

        tweet1.getRetweets().iterator().next().removeLike(user2);

        Assertions.assertEquals(1, tweet1.getRetweets().iterator().next().getLikes().size());

        Assertions.assertTrue(tweet1.getRetweets().iterator().next().getLikes().contains(user1));
        Assertions.assertFalse(tweet1.getRetweets().iterator().next().getLikes().contains(user2));
        Assertions.assertFalse(tweet1.getRetweets().iterator().next().getLikes().contains(user3));
    }

    @Test
    public void testUserCanNotRemoveAlreadyRemovedLikeFromRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        tweet1.addRetweet(user3);

        tweet1.getRetweets().iterator().next().addLike(user2);
        tweet1.getRetweets().iterator().next().removeLike(user2);

        Assertions.assertFalse(tweet1.removeLike(user2));
    }

    @Test
    public void testTweetGetDateTime() {
        LocalDateTime now = LocalDateTime.of(2023, 2, 25, 2, 40, 50);
        SystemDateTimeStub.setStub(now);

        User user4 = new User("user4", "1", new DateOfBirth(1,1,2004), new EmailAddress("email4@gmail.com"));
        Tweet tweet1 = new Tweet(new TweetBody("This is tweet #1"));
        tweet1.setDateTime(LocalDateTime.of(2023, 2, 25, 2, 40, 50));
        tweet1.setUser(user4);

        Assertions.assertEquals(SystemDateTime.now(), tweet1.getDateTime());
        SystemDateTimeStub.reset();
    }
}
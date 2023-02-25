package com.twitter.domain;

import com.twitter.util.SystemDate;
import com.twitter.util.SystemDateStub;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

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
        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        Assertions.assertEquals( 1, tweet1.getReplies().size());
        Assertions.assertEquals("This is a reply to tweet #1", tweet1.getReplies().iterator().next().getContent().getTweetBody());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddReplyToReply() {
        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        Reply reply2 = new Reply(user1, reply1, new TweetBody("This is a reply to reply #1"));

        reply1.addReply(reply2);

        Assertions.assertEquals( 1, tweet1.getReplies().size());
        Assertions.assertEquals( 1, reply1.getReplies().size());
        Assertions.assertEquals("This is a reply to reply #1", reply1.getReplies().iterator().next().getContent().getTweetBody());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddRetweetToTweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        Retweet retweet1 = new Retweet(user2);
        tweet1.addRetweet(retweet1);

        Assertions.assertEquals( 1, tweet1.getRetweets().size());

    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddReplyToRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        Retweet retweet1 = new Retweet(user2);
        tweet1.addRetweet(retweet1);

        Reply reply1 = new Reply(user1, retweet1, new TweetBody("This is a reply to retweet #1"));
        retweet1.addReply(reply1);

        Assertions.assertEquals( 1, tweet1.getRetweets().size());
        Assertions.assertEquals( 1, retweet1.getReplies().size());
        Assertions.assertEquals("This is a reply to retweet #1", retweet1.getReplies().iterator().next().getContent().getTweetBody());
    }


    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddRetweetToReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));

        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        Retweet retweet1 = new Retweet(user2);
        reply1.addRetweet(retweet1);
        Assertions.assertEquals( 1, reply1.getRetweets().size());

    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testAddRetweetToRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Retweet retweet1 = new Retweet(user2);
        tweet1.addRetweet(retweet1);

        Retweet retweet2 = new Retweet(user3);
        retweet1.addRetweet(retweet2);

        Assertions.assertEquals( 1, tweet1.getRetweets().size());
        Assertions.assertEquals( 1, retweet1.getRetweets().size());
    }

    /**
     * Done with JUnit tests
     */
    @Test
    public void testCreateRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Retweet retweet1 = new Retweet(user2);
        tweet1.addRetweet(retweet1);

        Retweet retweet2 = new Retweet(user3);
        tweet1.addRetweet(retweet2);

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

        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        reply1.addLike(user3);

        Assertions.assertEquals(1, reply1.getLikes().size());
        Assertions.assertTrue(reply1.getLikes().contains(user3));
        Assertions.assertFalse(reply1.getLikes().contains(user2));
    }

    @Test
    public void testUserCanNotLikeAlreadyLikedReply() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Reply reply1 = new Reply(user2, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

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

        Retweet retweet1 = new Retweet(user3);
        tweet1.addRetweet(retweet1);

        retweet1.addLike(user3);

        Assertions.assertEquals(1, retweet1.getLikes().size());
        Assertions.assertTrue(retweet1.getLikes().contains(user3));
        Assertions.assertFalse(retweet1.getLikes().contains(user2));

    }

    @Test
    public void testUserCanNotLikeAlreadyLikedRetweet() {
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Retweet retweet1 = new Retweet(user2);
        tweet1.addRetweet(retweet1);

        retweet1.addLike(user3);

        Assertions.assertFalse(retweet1.addLike(user3));
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
    public void testUserCanNotRemoveAlreadyRemovedLikeFromTweet(){
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

        Reply reply1 = new Reply(user1, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        reply1.addLike(user2);
        reply1.addLike(user3);

        Assertions.assertEquals(2, reply1.getLikes().size());

        reply1.removeLike(user2);

        Assertions.assertEquals(1, reply1.getLikes().size());

        Assertions.assertFalse(reply1.getLikes().contains(user1));
        Assertions.assertFalse(reply1.getLikes().contains(user2));
        Assertions.assertTrue(reply1.getLikes().contains(user3));
    }

    @Test
    public void testUserCanNotRemoveAlreadyRemovedLikeFromReply(){
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Reply reply1 = new Reply(user2, tweet1, new TweetBody("This is a reply to tweet #1"));
        tweet1.addReply(reply1);

        tweet1.setUser(user1);
        reply1.addLike(user3);
        reply1.removeLike(user3);

        Assertions.assertFalse(reply1.removeLike(user3));
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

        Retweet retweet1 = new Retweet(user3);
        tweet1.addRetweet(retweet1);

        retweet1.addLike(user1);
        retweet1.addLike(user2);

        Assertions.assertEquals(2, retweet1.getLikes().size());

        retweet1.removeLike(user2);

        Assertions.assertEquals(1, retweet1.getLikes().size());

        Assertions.assertTrue(retweet1.getLikes().contains(user1));
        Assertions.assertFalse(retweet1.getLikes().contains(user2));
        Assertions.assertFalse(retweet1.getLikes().contains(user3));
    }

    @Test
    public void testUserCanNotRemoveAlreadyRemovedLikeFromRetweet(){
        User user2 = new User("user2","2",new DateOfBirth(1,7,2002),new EmailAddress("email2@gmail.com"));
        User user3 = new User("user3","3",new DateOfBirth(1,7,2003),new EmailAddress("email3@gmail.com"));

        Retweet retweet1 = new Retweet(user3);
        tweet1.addRetweet(retweet1);

        retweet1.addLike(user2);
        retweet1.removeLike(user2);

        Assertions.assertFalse(tweet1.removeLike(user2));
    }

    @Test
    public void testTweetGetDateTime() {
        LocalDate now = LocalDate.of(2023, 2, 25);
        SystemDateStub.setStub(now);

        User user4 = new User("user4", "1", new DateOfBirth(1,1,2004), new EmailAddress("email4@gmail.com"));
        Tweet tweet1 = new Tweet(new TweetBody("This is tweet #1"));
        tweet1.setUser(user4);

        Assertions.assertEquals(SystemDate.now(), tweet1.getDateTime());
        SystemDateStub.reset();
    }
}
package com.twitter.persistence;

import com.twitter.Fixture;
import com.twitter.domain.Tweet;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
public class PostRepositoryTest {

    @Inject
    PostRepository postRepository;

    @Test
    public void testTimelineWithNullOffset() {
        List<Tweet> tweets = postRepository.postsForUserTimeline(Fixture.Users.USER3_ID, null, 5);

        Assertions.assertEquals(4, tweets.size());
    }

    @Test
    public void testTimelineWithOffset() {
        Tweet tweet = (Tweet) postRepository.findById(1001);

        List<Tweet> tweets = postRepository.postsForUserTimeline(Fixture.Users.USER3_ID, tweet, 5);

        Assertions.assertEquals(1, tweets.size());
        Assertions.assertEquals(1000, tweets.get(0).getId());
    }
}

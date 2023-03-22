package com.twitter.representation;

import com.twitter.domain.TweetBody;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TweetBodyMapperTest {
    private final TweetBodyMapper mapper = new TweetBodyMapperImpl();

    @Test
    void testToRepresentation() {
        // Given
        TweetBody tweetBody = new TweetBody("Hello, world!");

        // When
        TweetBodyRepresentation representation = mapper.toRepresentation(tweetBody);

        // Then
        assertNotNull(representation);
        assertEquals(tweetBody.getTweetContent(), representation.tweetContent);
    }

    @Test
    void testToModel() {
        // Given
        TweetBodyRepresentation representation = new TweetBodyRepresentation();
        representation.tweetContent = "Hello, world!";

        // When
        TweetBody tweetBody = mapper.toModel(representation);

        // Then
        assertNotNull(tweetBody);
        assertEquals(representation.tweetContent, tweetBody.getTweetContent());
    }

}

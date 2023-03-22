package com.twitter.representation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PostRepresentationTest {

    @Test
    public void testPostRepresentationConstructor() {
        PostRepresentation postRepresentation = new PostRepresentation();
        Assertions.assertNotNull(postRepresentation);
    }

    @Test
    public void testPostRepresentationId() {
        Integer postId = 123;
        PostRepresentation postRepresentation = new PostRepresentation();
        postRepresentation.id = postId;
        Assertions.assertEquals(postId, postRepresentation.id);
    }
}

package com.twitter.representation;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LikeRepresentationTest {

    private LikeRepresentation likeRep;

    @BeforeEach
    public void setUp() {
        likeRep = new LikeRepresentation();
    }

    @Test
    public void testConstructor() {
        assertNull(likeRep.likes);
    }

    @Test
    public void testAddLike() {
        UserBasicRepresentation userRep = new UserBasicRepresentation();
        userRep.id = 123;
        userRep.username = "testuser";

        likeRep.likes = new HashSet<>();
        likeRep.likes.add(userRep);

        assertEquals(1, likeRep.likes.size());
        UserBasicRepresentation addedUserRep = likeRep.likes.iterator().next();
        assertEquals(userRep.id, addedUserRep.id);
        assertEquals(userRep.username, addedUserRep.username);
    }
}

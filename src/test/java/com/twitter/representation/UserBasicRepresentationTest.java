package com.twitter.representation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserBasicRepresentationTest {

    private UserBasicRepresentation userRep;

    @BeforeEach
    public void setUp() {
        userRep = new UserBasicRepresentation();
    }

    @Test
    public void testConstructor() {
        assertNotNull(userRep);
        assertNull(userRep.id);
        assertNull(userRep.username);
    }

    @Test
    public void testSetId() {
        Integer userId = 123;

        userRep.id = userId;

        assertEquals(userId, userRep.id);
    }

    @Test
    public void testSetUsername() {
        String username = "testuser";

        userRep.username = username;

        assertEquals(username, userRep.username);
    }
}

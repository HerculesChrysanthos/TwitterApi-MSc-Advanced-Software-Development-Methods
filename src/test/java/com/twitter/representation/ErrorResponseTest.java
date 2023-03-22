package com.twitter.representation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorResponseTest {

    private ErrorResponse error;

    @BeforeEach
    public void setUp() {
        error = new ErrorResponse();
    }

    @Test
    public void testDefaultConstructor() {
        assertNull(error.getMessage());
    }

    @Test
    public void testCustomConstructor() {
        ErrorResponse customError = new ErrorResponse("Custom Error");
        assertEquals("Custom Error", customError.getMessage());
    }

    @Test
    public void testSetMessage() {
        String message = "Test error message";

        error.setMessage(message);

        assertEquals(message, error.getMessage());
    }
}

package com.twitter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class TwitterExceptionTest {

    @Test
    public void testTwitterExceptionNoArgs() {
        TwitterException exception = new TwitterException();
        Assertions.assertNotNull(exception);
    }

    @Test
    public void testTwitterExceptionWithMessage() {
        String message = "This is a test exception.";
        TwitterException exception = new TwitterException(message);
        Assertions.assertNotNull(exception);
        Assertions.assertEquals(message, exception.getMessage());
    }
}

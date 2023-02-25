package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailAddressTest {

    @Test
    public void testDefaultConstructor() {
        EmailAddress emailAddress = new EmailAddress();
        Assertions.assertNull(emailAddress.getEmail());
    }

    @Test
    public void testConstructorWithEmail() {
        String email = "test@example.com";
        EmailAddress emailAddress = new EmailAddress(email);
        Assertions.assertEquals(email, emailAddress.getEmail());
    }

    @Test
    public void testSetAndGetEmail() {
        String email = "test@example.com";
        EmailAddress emailAddress = new EmailAddress();
        emailAddress.setEmail(email);
        Assertions.assertEquals(email, emailAddress.getEmail());
    }

    @Test
    public void testIsValidWithValidEmail() {
        EmailAddress emailAddress = new EmailAddress("myEmailForTesting+123@example.com");
        boolean isValid = emailAddress.isValid(emailAddress.getEmail());

        Assertions.assertTrue(isValid);
    }

    @Test
    public void testIsValidWithInvalidEmail() {
        EmailAddress emailAddress = new EmailAddress("invalid_email");
        boolean isValid = emailAddress.isValid(emailAddress.getEmail());

        Assertions.assertFalse(isValid);
    }

    @Test
    public void testEquals() {
        EmailAddress emailAddress1 = new EmailAddress("myEmailForTesting+123@example.com");
        EmailAddress emailAddress2 = new EmailAddress("myEmailForTesting+123@example.com");
        EmailAddress emailAddress3 = new EmailAddress("test.email@example.com");

        Assertions.assertEquals(emailAddress1, emailAddress2);
        Assertions.assertNotEquals(emailAddress1, emailAddress3);
    }

    @Test
    public void testHashCode() {
        EmailAddress emailAddress1 = new EmailAddress("myEmailForTesting+123@example.com");
        EmailAddress emailAddress2 = new EmailAddress("myEmailForTesting+123@example.com");

        Assertions.assertEquals(emailAddress1.hashCode(), emailAddress2.hashCode());
    }
}

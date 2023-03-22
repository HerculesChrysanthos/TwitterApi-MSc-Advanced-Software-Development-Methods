package com.twitter.persistence;

import com.twitter.domain.EmailAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailCustomTypeTest {

    private final EmailCustomType emailCustomType = new EmailCustomType();

    @Test
    public void testAssemble() {
        Object result = emailCustomType.assemble(null, null);
        Assertions.assertNull(result);
    }

    @Test
    public void testDeepCopy() {
        String input = "test@example.com";
        EmailAddress email = new EmailAddress(input);
        Object result = emailCustomType.deepCopy(email);
        Assertions.assertEquals(email, result);
    }

    @Test
    public void testEquals() {
        String input = "test@example.com";
        EmailAddress email1 = new EmailAddress(input);
        EmailAddress email2 = new EmailAddress(input);
        Assertions.assertTrue(emailCustomType.equals(email1, email2));
        Assertions.assertFalse(emailCustomType.equals(email1, null));
        Assertions.assertFalse(emailCustomType.equals(email1, new Object()));
    }

    @Test
    public void testHashCode() {
        String input = "test@example.com";
        EmailAddress email = new EmailAddress(input);
        int hashCode = email.hashCode();
        int result = emailCustomType.hashCode(email);
        Assertions.assertEquals(hashCode, result);
    }

    @Test
    public void testIsMutable() {
        Assertions.assertFalse(emailCustomType.isMutable());
    }


    @Test
    public void testReplace() {
        String input = "test@example.com";
        EmailAddress email = new EmailAddress(input);
        Object result = emailCustomType.replace(email, null, null);
        Assertions.assertEquals(email, result);
    }

    @Test
    public void testReturnedClass() {
        Assertions.assertEquals(EmailAddress.class, emailCustomType.returnedClass());
    }
}

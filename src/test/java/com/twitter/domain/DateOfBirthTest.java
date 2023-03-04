package com.twitter.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DateOfBirthTest {
    @Test
    void testConstructorAndGetters() {
        DateOfBirth dob = new DateOfBirth(1, 2, 2000);
        Assertions.assertEquals(1, dob.getDay());
        Assertions.assertEquals(2, dob.getMonth());
        Assertions.assertEquals(2000, dob.getYear());
    }

    @Test
    void testSetters() {
        DateOfBirth dob = new DateOfBirth();
        dob.setDay(1);
        dob.setMonth(2);
        dob.setYear(2000);
        Assertions.assertEquals(1, dob.getDay());
        Assertions.assertEquals(2, dob.getMonth());
        Assertions.assertEquals(2000, dob.getYear());
    }

    @Test
    void testEqualsAndHashCode() {
        DateOfBirth dob1 = new DateOfBirth(1, 2, 2000);
        DateOfBirth dob2 = new DateOfBirth(1, 2, 2000);
        DateOfBirth dob3 = new DateOfBirth(2, 2, 2000);
        DateOfBirth dob4 = new DateOfBirth(1, 3, 2000);
        DateOfBirth dob5 = new DateOfBirth(1, 2, 2001);
        Assertions.assertEquals(dob1, dob2);
        Assertions.assertNotEquals(dob1, dob3);
        Assertions.assertNotEquals(dob1, dob4);
        Assertions.assertNotEquals(dob1, dob5);
        Assertions.assertEquals(dob1.hashCode(), dob2.hashCode());
        Assertions.assertNotEquals(dob1.hashCode(), dob3.hashCode());
        Assertions.assertNotEquals(dob1.hashCode(), dob4.hashCode());
        Assertions.assertNotEquals(dob1.hashCode(), dob5.hashCode());
    }
}

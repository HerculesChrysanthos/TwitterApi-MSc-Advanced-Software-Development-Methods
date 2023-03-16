package com.twitter.representation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DateOfBirthRepresentationTest {

    @Test
    public void testDateOfBirthRepresentation() {
        Integer day = 16;
        Integer month = 3;
        Integer year = 1990;

        DateOfBirthRepresentation dob = new DateOfBirthRepresentation();
        dob.day = day;
        dob.month = month;
        dob.year = year;

        assertEquals(day, dob.day);
        assertEquals(month, dob.month);
        assertEquals(year, dob.year);
    }
}

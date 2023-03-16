package com.twitter.representation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.twitter.domain.DateOfBirth;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class DateOfBirthMapperTest {

    private DateOfBirthMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = Mappers.getMapper(DateOfBirthMapper.class);
    }

    @Test
    public void testToRepresentation() {
        DateOfBirth dob = new DateOfBirth(16, 3, 1990);

        DateOfBirthRepresentation dobRep = mapper.toRepresentation(dob);

        assertNotNull(dobRep);
        assertEquals(dob.getDay(), dobRep.day);
        assertEquals(dob.getMonth(), dobRep.month);
        assertEquals(dob.getYear(), dobRep.year);
    }

    @Test
    public void testToModel() {
        DateOfBirthRepresentation dobRep = new DateOfBirthRepresentation();
        dobRep.day = 16;
        dobRep.month = 3;
        dobRep.year = 1990;

        DateOfBirth dob = mapper.toModel(dobRep);

        assertNotNull(dob);
        assertEquals(dobRep.day, dob.getDay());
        assertEquals(dobRep.month, dob.getMonth());
        assertEquals(dobRep.year, dob.getYear());
    }
}

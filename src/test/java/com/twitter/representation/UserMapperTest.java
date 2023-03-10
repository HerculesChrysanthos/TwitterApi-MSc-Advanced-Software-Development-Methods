package com.twitter.representation;

import com.twitter.domain.User;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserMapperTest {

    private final UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    public void testToRepresentation() {
        User user = new User();
        user.setId(1);
        user.setUsername("user");
        user.setPassword("password");
        UserRepresentation representation = userMapper.toRepresentation(user);
        assertNotNull(representation);
        assertEquals(1, representation.id);
        assertEquals("user", representation.username);
        assertNull(representation.password);
    }

    @Test
    public void testToModel() {
        UserRepresentation representation = new UserRepresentation();
        representation.id = 1;
        representation.username = "user";
        representation.password = "password";
        User user = userMapper.toModel(representation);
        assertNotNull(user);
        assertEquals(1, user.getId());
        assertEquals("user", user.getUsername());
        assertEquals("password", user.getPassword());
    }

    @Test
    public void testToRepresentationList() {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1);
        user1.setUsername("user1");
        user1.setPassword("password1");
        User user2 = new User();
        user2.setId(2);
        user2.setUsername("user2");
        user2.setPassword("password2");
        users.add(user1);
        users.add(user2);
        List<UserRepresentation> representations = userMapper.toRepresentationList(users);
        assertNotNull(representations);
        assertEquals(2, representations.size());
        UserRepresentation representation1 = representations.get(0);
        assertNotNull(representation1);
        assertEquals(1, representation1.id);
        assertEquals("user1", representation1.username);
        assertNull(representation1.password);
        UserRepresentation representation2 = representations.get(1);
        assertNotNull(representation2);
        assertEquals(2, representation2.id);
        assertEquals("user2", representation2.username);
        assertNull(representation2.password);
    }
}
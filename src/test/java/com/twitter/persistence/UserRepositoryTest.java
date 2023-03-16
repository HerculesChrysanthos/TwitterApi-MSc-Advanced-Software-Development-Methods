package com.twitter.persistence;

import com.twitter.domain.EmailAddress;
import com.twitter.domain.User;
import com.twitter.persistence.UserRepository;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

@QuarkusTest
public class UserRepositoryTest {

    @Inject
    UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        User user = userRepository.findByUsername("user1");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("user1", user.getUsername());
    }

    @Test
    public void testFindByEmail() {
        User user = userRepository.findByEmail("user1@email.com");
        Assertions.assertNotNull(user);
        Assertions.assertEquals("user1", user.getUsername());
        Assertions.assertEquals("user1@email.com", user.getEmail().getEmail());
    }
}

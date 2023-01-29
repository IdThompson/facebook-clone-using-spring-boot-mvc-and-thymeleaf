package com.fragile.STMS.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import com.fragile.STMS.entity.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class UserRepoTest {

    @Autowired
    private UserRepo userRepo;

    @BeforeEach
    void setUp(){

    }

    @Test
    void getUserByEmailAddress_whenEmailExists_returnUser() {
        User user = User.builder().emailAddress( "johndoe@example.com").userId(1L).firstName("john").password("password").build();       //("John", "Doe", "johndoe@example.com", "password");
        userRepo.save(user);

        Optional<User> result = userRepo.getUserByEmailAddress("johndoe@example.com");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUserByEmailAddressAndPassword_whenEmailAndPasswordMatch_returnUser() {
        User user = User.builder().emailAddress( "johndoe@example.com").userId(1L).firstName("john").password("password").build();
        userRepo.save(user);

        Optional<User> result = userRepo.getUserByEmailAddressAndPassword("johndoe@example.com", "password");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
}

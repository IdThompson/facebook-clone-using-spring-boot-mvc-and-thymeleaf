package com.fragile.STMS.Repository;

import com.fragile.STMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> getUserByEmailAddress(String emailAddress);
    Optional<User> getUserByEmailAddressAndPassword(String emailAddress, String password);
}
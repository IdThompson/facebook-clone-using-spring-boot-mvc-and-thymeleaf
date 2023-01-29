package com.fragile.STMS.ServiceImp;

import com.fragile.STMS.Repository.UserRepo;
import com.fragile.STMS.dto.ResponseDto;
import com.fragile.STMS.dto.UserLoginDto;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIpm implements UserService {
    @Autowired
    UserRepo userRepo;

    public ResponseDto signUp(User user) {
        Optional<User> userDb = userRepo.getUserByEmailAddress(user.getEmailAddress());
        ResponseDto response = new ResponseDto();

        try {
            if (userDb.isPresent()) {
                throw new Exception("This email is already registered");
            }
            User savedUser = userRepo.save(user);
            response.setUser(savedUser);
            response.setMessage("Registration successful");
            response.setStatus(true);
            return response;

        } catch (Exception e) {
            response.setMessage(e.getMessage());
            response.setStatus(false);
            return response;
        }
    }

    public ResponseDto signIn(User user) {
        Optional<User> userDb = userRepo.getUserByEmailAddressAndPassword
                (user.getEmailAddress(), user.getPassword());

        ResponseDto response = new ResponseDto();

        if (userDb.isPresent()) {
            response.setStatus(true);
            response.setUser(userDb.get());
            response.setMessage("LogIn successful");
            return response;
        }
        response.setMessage("Invalid email or password");
        return response;

    }
}
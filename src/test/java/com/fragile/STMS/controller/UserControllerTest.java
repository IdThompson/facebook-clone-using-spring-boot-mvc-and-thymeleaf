package com.fragile.STMS.controller;

import com.fragile.STMS.dto.ResponseDto;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.service.UserService;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Test
    void registerUser_whenSuccess_redirectToHome() throws Exception {
        ResponseDto response =  ResponseDto.builder().status(true).message("User registered successfully.").build();     //(true, "User registered successfully.");
        when(userService.signUp(any(User.class))).thenReturn(response);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(post("/register")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("email", "johndoe@example.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"))
                .andExpect(model().attribute("message", "User registered successfully."));
    }

    @Test
    void registerUser_whenFailure_showErrorMessage() throws Exception {
        ResponseDto response = ResponseDto.builder().status(true).message("User registered successfully.").build();
        when(userService.signUp(any(User.class))).thenReturn(response);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        mockMvc.perform(post("/register")
                        .param("firstName", "John")
                        .param("lastName", "Doe")
                        .param("email", "johndoe@example.com")
                        .param("password", "password"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("message", "Email already exists."))
                .andExpect(view().name("register"));
    }
}
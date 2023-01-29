package com.fragile.STMS.controller;

import com.fragile.STMS.dto.ResponseDto;
import com.fragile.STMS.dto.UserLoginDto;
import com.fragile.STMS.entity.User;
import com.fragile.STMS.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String registerUser(User user, Model model, RedirectAttributes redirectAttributes) {
        ResponseDto response = userService.signUp(user);

        if (response.isStatus()) {
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            return "redirect:/";
        }
        model.addAttribute("message", response.getMessage());
        return "register";

    }
    /**
     * Method to get the index page
     * @param model
     * @return
     */
    @GetMapping("/")
    public String getLogInPage(Model model) {
        model.addAttribute("user", new UserLoginDto());
        return "index";
    }

    /**
     * Method to log in a user
     * @param user
     * @param model
     * @param redirectAttributes
     * @param request
     * @return
     */
    @PostMapping("/login")
    public String logInUser(User user, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        HttpSession session = request.getSession();
        ResponseDto response = userService.signIn(user);
        if (response.isStatus()) {
            redirectAttributes.addFlashAttribute("message", response.getMessage());
            redirectAttributes.addFlashAttribute("user", response.getUser());
            session.setAttribute("logUser", response.getUser());
            return "redirect:/home";
        }
        model.addAttribute("message", response.getMessage());
        return "index";

    }

    /**
     * Method to get the logout page
     * @param model
     * @param httpSession
     * @return
     */
    @GetMapping("/logout")
    public String logUserOut(Model model, HttpSession httpSession) {

        if (httpSession != null) {
            httpSession.invalidate();
        }

        model.addAttribute("user", new User());
        model.addAttribute("invalid", null);
        return "redirect:/";

    }



}

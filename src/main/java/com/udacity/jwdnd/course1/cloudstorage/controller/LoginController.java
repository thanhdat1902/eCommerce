package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value={"/", "/login"})
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping()
    public String loginView() {
        return "login";
    }

    @PostMapping()
    public String loginUser(@ModelAttribute User user, Model model) {
        String loginError = null;
        String loginMessage = null;



        if (userService.isUsernameAvailable(user.getUsername())) {
            loginMessage = "The username already exists.";
        }


        if (loginError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", "signupError");
        }

        return "signup";
    }
}

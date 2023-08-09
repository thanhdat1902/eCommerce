package com.udacity.jwdnd.course1.cloudstorage.controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class GlobalErrorHandler implements ErrorController {
    @GetMapping("/error")
    public String showError(Model model){
        model.addAttribute("login",true);
        return "errorPage";
    }
    @Override
    public String getErrorPath() {
        return null;
    }
}

package com.kibikalo.store.controller;

import com.kibikalo.store.service.RegistrationService;
import com.kibikalo.store.service.UserService;
import com.kibikalo.store.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") User user, BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }
        registrationService.register(user);
        return "register_success";
    }
}

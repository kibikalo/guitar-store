package com.kibikalo.store.controller;

import com.kibikalo.store.model.User;
import com.kibikalo.store.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}")
    public String showAccountPage(@PathVariable Long id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "user-account";
    }

    @PostMapping("/user/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute("user") User updatedUser, BindingResult result) {
        if (result.hasErrors()) {
            return "user-account";
        }
        Long userId = Long.parseLong(id);
        User user = userService.updateUser(userId, updatedUser);
        return "redirect:/account/user/" + user.getId();
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/";
    }
}

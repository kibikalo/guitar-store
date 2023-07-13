package com.kibikalo.store.service;

import com.kibikalo.store.model.UserRole;
import com.kibikalo.store.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private EmailValidator emailValidator;

    public void register(User user) {
        boolean isValidEmail  = emailValidator.validateEmail(user.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        userService.signUpUser(
                new User(
                    user.getFirstName(),
                    user.getLastName(),
                    user.getUsername(),
                    user.getPassword(),
                    UserRole.USER
                )
        );
    }
}

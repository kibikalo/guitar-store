package com.kibikalo.store.service;

import com.kibikalo.store.model.User;
import com.kibikalo.store.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "email with %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public void signUpUser(User user) {
        if (userExists(user.getEmail())) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    private boolean userExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Transactional
    public User updateUser(Long id , User updatedUser) {
        User user = findById(id);

        user.setFirstName(updatedUser.getFirstName());
        user.setLastName(updatedUser.getLastName());
        user.setEmail(updatedUser.getEmail());

        setPasswordIfNotEmpty(updatedUser.getPassword(), user);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User with id " + id + " not found"));
    }

    private void setPasswordIfNotEmpty(String newPassword, User user) {
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        }
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
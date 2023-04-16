package com.zachcarrera.bookclub.services;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.zachcarrera.bookclub.models.LoginUser;
import com.zachcarrera.bookclub.models.User;
import com.zachcarrera.bookclub.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // register logic
    public User register(User newUser, BindingResult result) {
        // find user in DB
        Optional<User> optionalUser = userRepository.findByEmail(newUser.getEmail());

        // reject if present
        if (optionalUser.isPresent()) {
            result.rejectValue("email", "unique", "This email is already registered");
        }

        // reject if password doesn't match confirm
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("confirm", "matches", "This confirm password does not match");
        }

        // if result has errors, return
        if (result.hasErrors()) {
            return null;
        }

        // hash and set password
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        newUser.setPassword(hashed);
        return userRepository.save(newUser);
    }

    // login logic
    public User login(LoginUser newLogin, BindingResult result) {

        // find user in DB
        Optional<User> optionalUser = userRepository.findByEmail(newLogin.getEmail());

        // if email is not present reject
        if (!optionalUser.isPresent()) {
            result.rejectValue("email", "unique", "This email is not registered");
            return null;
        }

        User user = optionalUser.get();

        // check hashed password
        if (!BCrypt.checkpw(newLogin.getPassword(), user.getPassword())) {
            result.rejectValue("password", "matches", "The password does not match for this email");
        }

        // if errors return
        if (result.hasErrors()) {
            return null;
        }

        return user;
    }
}
package com.zachcarrera.bookclub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zachcarrera.bookclub.models.LoginUser;
import com.zachcarrera.bookclub.models.User;
import com.zachcarrera.bookclub.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String loginPage(
            @ModelAttribute("newUser") User newUser,
            @ModelAttribute("newLogin") LoginUser newLogin
            ) {
        return "logreg.jsp";
    }

    @PostMapping("/register")
    public String processRegister(
            @Valid @ModelAttribute("newUser") User newUser,
            BindingResult result,
            Model model,
            HttpSession session
            ) {
        User registeredUser = userService.register(newUser, result);
        if (result.hasErrors()) {
            model.addAttribute("newLogin", new LoginUser());
            return "logreg.jsp";
        }
        session.setAttribute("userId", registeredUser.getId());
        session.setAttribute("userName", registeredUser.getName());

        return "redirect:/books";
    }

    @PostMapping("/login")
    public String processLogin(
            @Valid @ModelAttribute("newLogin") LoginUser newLogin,
            BindingResult result,
            Model model,
            HttpSession session
            ) {
        User loginUser = userService.login(newLogin, result);
        if (result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "logreg.jsp";
        }
        session.setAttribute("userId", loginUser.getId());
        session.setAttribute("userName", loginUser.getName());

        return "redirect:/books";
    }


    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
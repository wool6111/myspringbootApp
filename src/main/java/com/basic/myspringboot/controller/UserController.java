package com.basic.myspringboot.controller;

import com.basic.myspringboot.entity.User;
import com.basic.myspringboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        // 검증 오류가 발생한 경우
        if (result.hasErrors()) {
            return "add-user";
        }
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @GetMapping("/signup")
    public String showSignUpForm(User addUser) {
        return "add-user";
    }

    @GetMapping("/users/index")
    public ModelAndView index() {
        return new ModelAndView("index","users", userRepository.findAll());
    }

    @GetMapping("/thymeleaf")
    public String leaf(Model model) {
        model.addAttribute("name","스프링부트");
        return "leaf";
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error/generic_error");
        model.addObject("errMsg", ex.getMessage());
        return model;
    }

    @GetMapping("/mypage")
    public String mypage() {
        return "mypage";
    }

}

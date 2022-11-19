package com.example.scoredei.web;

import com.example.data.Users;
import com.example.scoredei.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("/register")
    public String register(Model m){
        m.addAttribute("user", new Users());
        return "/register";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String loginVerification(){
        return "redirect:/";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute Users user, Model m) {
        int returnCode = usersService.addUser(user);

        if(returnCode == 1 || returnCode == 2 || returnCode == 3){
            m.addAttribute("errorCode", returnCode);
            m.addAttribute("user", user);
            return "/register";
        }
        return "redirect:/";
    }





}

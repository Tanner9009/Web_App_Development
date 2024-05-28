package com.Gamefinders.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
    
    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/boardgames")
    public String boardgames(){
        return "boardgames";
    }
}
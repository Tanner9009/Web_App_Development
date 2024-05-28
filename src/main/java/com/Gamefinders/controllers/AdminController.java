package com.Gamefinders.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gamefinders.domain.classes.BoardGame;

@Controller
@RequestMapping("/adminpanel")
public class AdminController {
    
    @GetMapping
    public String showAdminPanel(Model model){
        model.addAttribute("boardgame", new BoardGame());
        return "adminPanel";
    }
}

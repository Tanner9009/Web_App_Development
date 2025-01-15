package com.Gamefinders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gamefinders.services.interfaces.BoardGameService;

@Controller
@RequestMapping("/")
public class HomeController {

    private final BoardGameService boardGameService;

    @Autowired
    public HomeController(BoardGameService boardGameService) {
        this.boardGameService = boardGameService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("boardgames", boardGameService.findAll());
        return "home";
    }
}
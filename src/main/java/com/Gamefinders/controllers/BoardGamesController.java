package com.Gamefinders.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.Gamefinders.services.interfaces.BoardGameService;

@Controller
public class BoardGamesController {
    
    private final BoardGameService boardGameService;

    public BoardGamesController(BoardGameService boardGameService){
        this.boardGameService = boardGameService;
    }

    @GetMapping("/boardgames")
    public String browse(Model model){
        model.addAttribute("boardGames", boardGameService.findAll());
        return "boardgames";
    }
}

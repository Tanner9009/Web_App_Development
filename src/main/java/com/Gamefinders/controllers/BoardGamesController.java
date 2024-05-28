package com.Gamefinders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gamefinders.services.interfaces.BoardGameService;

@Controller
@RequestMapping("/boardgames")
public class BoardGamesController {
    
    private final BoardGameService boardGameService;

    @Autowired
    public BoardGamesController(BoardGameService boardGameService){
        this.boardGameService = boardGameService;
    }

    @GetMapping
    public String seeBoardGames(Model model){
        model.addAttribute("boardGames", boardGameService.findAll());
        return "boardgames";
    }
}

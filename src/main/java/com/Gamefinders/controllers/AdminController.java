package com.Gamefinders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.services.interfaces.BoardGameService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BoardGameService boardGameService;

    @GetMapping
    public String showAdminDashboard(Model model) {
        model.addAttribute("newBoardGame", new BoardGame());
        model.addAttribute("boardGames", boardGameService.findAll());
        return "admin";
    }

    @PostMapping("/addBoardGame")
    public String addBoardGame(@ModelAttribute BoardGame newBoardGame) {
        boardGameService.save(newBoardGame);
        return "redirect:/admin";
    }

    @PostMapping("/removeBoardGame")
    public String removeBoardGame(@RequestParam String boardGameName) {
        boardGameService.deleteByName(boardGameName);
        return "redirect:/admin";
    }
}
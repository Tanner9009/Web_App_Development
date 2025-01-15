package com.Gamefinders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.services.interfaces.BoardGameService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
    public String addBoardGame(@ModelAttribute BoardGame newBoardGame, @RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            try {
                // Save the image to the static/images/boardgames directory
                String imagePath = "src/main/resources/static/images/boardgames/" + image.getOriginalFilename();
                Path path = Paths.get(imagePath);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());

                // Set the image URL to the BoardGame object
                newBoardGame.setImageUrl("/images/boardgames/" + image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }

        boardGameService.save(newBoardGame);
        return "redirect:/admin";
    }

    @PostMapping("/removeBoardGame")
    public String removeBoardGame(@RequestParam String boardGameName) {
        boardGameService.deleteByName(boardGameName);
        return "redirect:/admin";
    }
}
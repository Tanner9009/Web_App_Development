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
import com.Gamefinders.domain.classes.Review;
import com.Gamefinders.services.interfaces.BoardGameService;
import com.Gamefinders.services.interfaces.ReviewService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BoardGameService boardGameService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public String showAdminDashboard(Model model) {
        model.addAttribute("newBoardGame", new BoardGame());
        model.addAttribute("boardGames", boardGameService.findAll());
        model.addAttribute("reviews", reviewService.findAll());
        return "admin";
    }

    @GetMapping("/modifyBoardGames")
    public String modifyBoardGames(Model model) {
        model.addAttribute("newBoardGame", new BoardGame());
        model.addAttribute("boardGames", boardGameService.findAll());
        return "modifyBoardGames";
    }

    @GetMapping("/modifyReviews")
    public String modifyReviews(Model model) {
        List<Review> reviews = reviewService.findAll();
        Map<String, String> gameNames = reviews.stream()
                .collect(Collectors.toMap(Review::getId, review -> boardGameService.findById(review.getGameId()).map(BoardGame::getName).orElse("Unknown Game")));
        model.addAttribute("reviews", reviews);
        model.addAttribute("gameNames", gameNames);
        return "modifyReviews";
    }

    @PostMapping("/addBoardGame")
    public String addOrUpdateBoardGame(@ModelAttribute BoardGame newBoardGame, @RequestParam("image") MultipartFile image) {
        Optional<BoardGame> existingGameOpt = boardGameService.findByName(newBoardGame.getName()).stream().findFirst();
        BoardGame boardGame;

        if (existingGameOpt.isPresent()) {
            boardGame = existingGameOpt.get();
            // Update existing game details with provided ones
            if (newBoardGame.getDesigner() != null) boardGame.setDesigner(newBoardGame.getDesigner());
            if (newBoardGame.getArtist() != null) boardGame.setArtist(newBoardGame.getArtist());
            if (newBoardGame.getPublisher() != null) boardGame.setPublisher(newBoardGame.getPublisher());
            if (newBoardGame.getPlayingMinTime() != null) boardGame.setPlayingMinTime(newBoardGame.getPlayingMinTime());
            if (newBoardGame.getPlayingMaxTime() != null) boardGame.setPlayingMaxTime(newBoardGame.getPlayingMaxTime());
            if (newBoardGame.getPlayerNoMin() != null) boardGame.setPlayerNoMin(newBoardGame.getPlayerNoMin());
            if (newBoardGame.getPlayerNoMax() != null) boardGame.setPlayerNoMax(newBoardGame.getPlayerNoMax());
            if (newBoardGame.getMinimumAge() != null) boardGame.setMinimumAge(newBoardGame.getMinimumAge());
            if (newBoardGame.getGameDescription() != null) boardGame.setGameDescription(newBoardGame.getGameDescription());
            if (newBoardGame.getReleaseDate() != null) boardGame.setReleaseDate(newBoardGame.getReleaseDate());
            if (newBoardGame.getTypes() != null) boardGame.setTypes(newBoardGame.getTypes());
            if (newBoardGame.getCategories() != null) boardGame.setCategories(newBoardGame.getCategories());
            if (newBoardGame.getMechanics() != null) boardGame.setMechanics(newBoardGame.getMechanics());
            if (newBoardGame.getUserScore() != null) boardGame.setUserScore(newBoardGame.getUserScore());
        } else {
            boardGame = newBoardGame;
        }

        if (!image.isEmpty()) {
            try {
                // Save the image to the static/images/boardgames directory
                String imagePath = "src/main/resources/static/images/boardgames/" + image.getOriginalFilename();
                Path path = Paths.get(imagePath);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());

                // Set the image URL to the BoardGame object
                boardGame.setImageUrl("/images/boardgames/" + image.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }

        boardGameService.save(boardGame);
        return "redirect:/admin/modifyBoardGames";
    }

    @PostMapping("/removeBoardGame")
    public String removeBoardGame(@RequestParam String boardGameName) {
        boardGameService.deleteByName(boardGameName);
        return "redirect:/admin/modifyBoardGames";
    }

    @PostMapping("/removeReview")
    public String removeReview(@RequestParam String reviewId) {
        reviewService.deleteById(reviewId);
        return "redirect:/admin/modifyReviews";
    }

    @PostMapping("/getReviewsByGame")
    public String getReviewsByGame(@RequestParam String gameName, Model model) {
        List<BoardGame> boardGames = boardGameService.findByName(gameName);
        if (!boardGames.isEmpty()) {
            BoardGame boardGame = boardGames.get(0);
            List<Review> reviews = reviewService.findByGameId(boardGame.getId());
            model.addAttribute("reviews", reviews);
            model.addAttribute("gameNames", reviews.stream().collect(Collectors.toMap(Review::getId, r -> boardGame.getName())));
        } else {
            model.addAttribute("reviews", List.of());
            model.addAttribute("gameNames", Map.of());
        }
        return "modifyReviews";
    }

    @PostMapping("/getReviewsByAuthor")
    public String getReviewsByAuthor(@RequestParam String authorUsername, Model model) {
        List<Review> reviews = reviewService.findByAuthorUsername(authorUsername);
        model.addAttribute("reviews", reviews);
        model.addAttribute("gameNames", reviews.stream().collect(Collectors.toMap(Review::getId, r -> {
            BoardGame game = boardGameService.findById(r.getGameId()).orElse(null);
            return game != null ? game.getName() : "Unknown Game";
        })));
        return "modifyReviews";
    }
}
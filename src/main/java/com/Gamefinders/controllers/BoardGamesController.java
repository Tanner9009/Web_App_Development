package com.Gamefinders.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.domain.classes.Review;
import com.Gamefinders.domain.classes.User;
import com.Gamefinders.services.interfaces.BoardGameService;
import com.Gamefinders.services.interfaces.ReviewService;
import com.Gamefinders.services.interfaces.UserService;

@Controller
@RequestMapping("/boardgames")
public class BoardGamesController {
    
    private final BoardGameService boardGameService;
    private final UserService userService;
    private final ReviewService reviewService;

    @Autowired
    public BoardGamesController(BoardGameService boardGameService, UserService userService, ReviewService reviewService){
        this.boardGameService = boardGameService;
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping
    public String seeBoardGames(Model model){
        model.addAttribute("boardgames", boardGameService.findAll());
        return "boardgames";
    }

    @GetMapping("/details/{name}")
    public String getBoardGameDetails(@PathVariable String name, Model model, Authentication authentication){
        List<BoardGame> boardGames = boardGameService.findByName(name);
        if (boardGames.isEmpty()) {
            return "error";
        }
        BoardGame boardGame = boardGames.get(0);
        model.addAttribute("boardgame", boardGame);

        List<Review> reviews = reviewService.findByGameId(boardGame.getId());
        model.addAttribute("reviews", reviews);

        if (authentication != null && authentication.isAuthenticated()) {
            User user = userService.findByUsername(authentication.getName());
            if (user != null) {
                if (user.getCollections() == null) {
                    user.setCollections(new ArrayList<>());
                }
                if (user.getWishlist() == null) {
                    user.setWishlist(new ArrayList<>());
                }
                model.addAttribute("inCollection", user.getCollections().contains(boardGame));
                model.addAttribute("inWishlist", user.getWishlist().contains(boardGame));

                // Check if the user has already reviewed this game
                Review existingReview = reviews.stream()
                    .filter(review -> review.getAuthorUsername().equals(user.getUsername()))
                    .findFirst()
                    .orElse(null);
                model.addAttribute("existingReview", existingReview);
            }
        } else {
            model.addAttribute("inCollection", false);
            model.addAttribute("inWishlist", false);
            model.addAttribute("existingReview", null);
        }

        return "boardgame";
    }

    @PostMapping("/details/{name}/collection")
    public String toggleCollection(@PathVariable String name, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return "error";
        }
        List<BoardGame> boardGames = boardGameService.findByName(name);
        if (!boardGames.isEmpty()) {
            BoardGame boardGame = boardGames.get(0);
            if (user.getCollections() == null) {
                user.setCollections(new ArrayList<>());
            }
            if (user.getCollections().contains(boardGame)) {
                user.getCollections().remove(boardGame);
            } else {
                user.getCollections().add(boardGame);
            }
            userService.save(user);
        }
        return "redirect:/boardgames/details/" + name;
    }

    @PostMapping("/details/{name}/wishlist")
    public String toggleWishlist(@PathVariable String name, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return "error";
        }
        List<BoardGame> boardGames = boardGameService.findByName(name);
        if (!boardGames.isEmpty()) {
            BoardGame boardGame = boardGames.get(0);
            if (user.getWishlist() == null) {
                user.setWishlist(new ArrayList<>());
            }
            if (user.getWishlist().contains(boardGame)) {
                user.getWishlist().remove(boardGame);
            } else {
                user.getWishlist().add(boardGame);
            }
            userService.save(user);
        }
        return "redirect:/boardgames/details/" + name;
    }

    @PostMapping("/details/{name}/reviews")
    public String addOrUpdateReview(@PathVariable String name, @RequestParam String review, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return "error";
        }
        List<BoardGame> boardGames = boardGameService.findByName(name);
        if (!boardGames.isEmpty()) {
            BoardGame boardGame = boardGames.get(0);
            Review existingReview = reviewService.findByAuthorUsername(user.getUsername()).stream()
                .filter(r -> r.getGameId().equals(boardGame.getId()))
                .findFirst()
                .orElse(null);
            if (existingReview != null) {
                existingReview.setText(review);
                reviewService.save(existingReview);
            } else {
                Review newReview = new Review();
                newReview.setAuthorUsername(user.getUsername());
                newReview.setGameId(boardGame.getId());
                newReview.setText(review);
                reviewService.save(newReview);
            }
        }
        return "redirect:/boardgames/details/" + name;
    }

    @PostMapping("/details/{name}/reviews/delete")
    public String deleteReview(@PathVariable String name, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }
        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return "error";
        }
        List<BoardGame> boardGames = boardGameService.findByName(name);
        if (!boardGames.isEmpty()) {
            BoardGame boardGame = boardGames.get(0);
            Review existingReview = reviewService.findByAuthorUsername(user.getUsername()).stream()
                .filter(r -> r.getGameId().equals(boardGame.getId()))
                .findFirst()
                .orElse(null);
            if (existingReview != null) {
                reviewService.deleteById(existingReview.getId());
            }
        }
        return "redirect:/boardgames/details/" + name;
    }
}
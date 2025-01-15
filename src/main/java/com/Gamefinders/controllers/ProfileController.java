package com.Gamefinders.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.Gamefinders.domain.classes.User;
import com.Gamefinders.domain.classes.Review;
import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.services.interfaces.UserService;
import com.Gamefinders.services.interfaces.ReviewService;
import com.Gamefinders.services.interfaces.BoardGameService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private final ReviewService reviewService;
    private final BoardGameService boardGameService;

    @Autowired
    public ProfileController(UserService userService, ReviewService reviewService, BoardGameService boardGameService) {
        this.userService = userService;
        this.reviewService = reviewService;
        this.boardGameService = boardGameService;
    }

    @GetMapping("/{username}")
    public String getProfile(@PathVariable String username, Model model, Authentication authentication) {
        User user = userService.findByUsername(username);
        if (user == null) {
            return "error";
        }
        List<Review> reviews = reviewService.findByAuthorUsername(username);
        Map<String, String> gameNames = reviews.stream()
                .collect(Collectors.toMap(Review::getId, review -> boardGameService.findById(review.getGameId()).map(BoardGame::getName).orElse("Unknown Game")));
        Map<String, String> gameLinks = reviews.stream()
                .collect(Collectors.toMap(Review::getId, review -> boardGameService.findById(review.getGameId()).map(game -> "/boardgames/details/" + game.getName()).orElse("#")));
        
        model.addAttribute("user", user);
        model.addAttribute("collections", user.getCollections());
        model.addAttribute("wishlist", user.getWishlist());
        model.addAttribute("reviews", reviews);
        model.addAttribute("gameNames", gameNames);
        model.addAttribute("gameLinks", gameLinks);
        return "profile";
    }

    @GetMapping
    public String redirectToProfile(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return "redirect:/profile/" + authentication.getName();
        }
        return "redirect:/login";
    }

    @PostMapping("/uploadProfilePicture")
    public String uploadProfilePicture(@RequestParam("profilePicture") MultipartFile profilePicture, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        User user = userService.findByUsername(authentication.getName());
        if (user == null) {
            return "error";
        }

        if (!profilePicture.isEmpty()) {
            try {
                // Save the image to the static/images/profile_pictures directory
                String imagePath = "src/main/resources/static/images/profile_pictures/" + profilePicture.getOriginalFilename();
                Path path = Paths.get(imagePath);
                Files.createDirectories(path.getParent());
                Files.write(path, profilePicture.getBytes());

                // Set the profile picture URL to the User object
                user.setProfilePictureUrl("/images/profile_pictures/" + profilePicture.getOriginalFilename());
                userService.save(user);
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the error appropriately
            }
        }

        return "redirect:/profile/" + user.getUsername();
    }
}
package com.Gamefinders.bootstrap;

import org.springframework.stereotype.Component;

import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.services.interfaces.BoardGameService;
import com.Gamefinders.services.interfaces.UserService;

@Component
public class DataLoader {
    private final BoardGameService boardGameService;
    private final UserService userService;
    
    public DataLoader(BoardGameService boardGameService, UserService userService){
        this.boardGameService = boardGameService;
        this.userService = userService;
    }

}
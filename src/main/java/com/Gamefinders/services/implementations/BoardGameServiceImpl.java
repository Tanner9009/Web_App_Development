package com.Gamefinders.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.OAuth2ResourceServerSecurityMarker;

import com.Gamefinders.repositories.BoardGameRepository;
import com.Gamefinders.services.interfaces.BoardGameService;
import com.Gamefinders.domain.classes.BoardGame;
import java.util.List;
import java.util.Optional;

public class BoardGameServiceImpl implements BoardGameService{
    private final BoardGameRepository boardGameRepository;

    @Autowired
    public BoardGameServiceImpl(BoardGameRepository boardGameRepository){
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public List<BoardGame> findAll(){
        return (List<BoardGame>) boardGameRepository.findAll();
    }

    @Override
    public void save(BoardGame boardGame){
        boardGameRepository.save(boardGame);
    }

    @Override
    public void saveAll(Iterable<BoardGame> boardGames) {
        boardGameRepository.saveAll(boardGames);
    }

    @Override
    public Optional<BoardGame> findById(String id) {
        return (Optional<BoardGame>) boardGameRepository.findById(id);
    }
}

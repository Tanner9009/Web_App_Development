package com.Gamefinders.services.implementations;
import org.springframework.stereotype.Service;

import com.Gamefinders.domain.classes.BoardGame;
import com.Gamefinders.repositories.BoardGameRepository;
import com.Gamefinders.services.interfaces.BoardGameService;

import java.util.Optional;
import java.util.List;

@Service
public class MongoBoardGameService implements BoardGameService{
    
    private final BoardGameRepository boardGameRepository;

    private MongoBoardGameService(BoardGameRepository boardGameRepository){
        this.boardGameRepository = boardGameRepository;
    }

    @Override
    public void saveAll(Iterable<BoardGame> boardGames) {
        boardGameRepository.saveAll(boardGames);
    }

    @Override
    public List<BoardGame> findAll() {
        return boardGameRepository.findAll();
    }

    @Override
    public void save(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
    }

    @Override
    public Optional<BoardGame> findById(String id) {
        return boardGameRepository.findById(id);
    }
}

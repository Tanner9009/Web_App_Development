package com.Gamefinders.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Gamefinders.repositories.BoardGameRepository;
import com.Gamefinders.services.interfaces.BoardGameService;
import com.Gamefinders.domain.classes.BoardGame;
import java.util.List;
import java.util.Optional;

@Service
public class BoardGameServiceImpl implements BoardGameService {

    @Autowired
    private BoardGameRepository boardGameRepository;

    @Override
    public List<BoardGame> findAll() {
        return boardGameRepository.findAll();
    }

    @Override
    public void save(BoardGame boardGame) {
        boardGameRepository.save(boardGame);
    }

    @Override
    public void deleteByName(String name) {
        boardGameRepository.deleteByName(name);
    }

    @Override
    public void saveAll(Iterable<BoardGame> boardGames) {
        boardGameRepository.saveAll(boardGames);
    }

    @Override
    public Optional<BoardGame> findById(String id) {
        return boardGameRepository.findById(id);
    }
}
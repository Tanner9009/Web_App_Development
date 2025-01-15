package com.Gamefinders.services.interfaces;

import com.Gamefinders.domain.classes.BoardGame;
import java.util.List;
import java.util.Optional;

public interface BoardGameService {
    List<BoardGame> findAll();
    void save(BoardGame boardGame);
    void saveAll(Iterable<BoardGame> boardGames);
    Optional<BoardGame> findById(String id);
    void deleteByName(String name);
    List<BoardGame> findByName(String name);
}
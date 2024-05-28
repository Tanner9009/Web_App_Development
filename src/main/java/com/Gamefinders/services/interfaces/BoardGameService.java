package com.Gamefinders.services.interfaces;

import com.Gamefinders.domain.classes.BoardGame;
import java.util.List;
import java.util.Optional;

public interface BoardGameService {
    void saveAll(Iterable<BoardGame> boardGames);
    List<BoardGame> findAll();

    void save(BoardGame boardGame);
    Optional<BoardGame> findById(String id);
}

package com.Gamefinders.repositories;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.Gamefinders.domain.classes.BoardGame;

public interface BoardGameRepository extends MongoRepository<BoardGame, String> {
    
    @SuppressWarnings("null")
    List<BoardGame> findAll();    
    List<BoardGame> findByName(String name);
    
}
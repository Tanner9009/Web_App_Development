package com.Gamefinders.domain.classes;

//Imports Beginning

//Utilities
import java.util.ArrayList;
import java.util.Objects;
import java.time.LocalDate;

//MongoDB 
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Lombok
import lombok.Getter;
import lombok.Setter;

//Imports Ending

@Getter
@Setter
@Document("boardgames")
public class BoardGame {
    
    @Id
    private String id;

    //Board game Metadata
    private String name;
    private String designer, artist, publisher;
    private String gameDescription;
    private LocalDate releaseDate;

    //Board game general information
    private Integer playingTimeMin, playingTimeMax;
    private Integer playerNoMin, playerNOMax;
    private Integer minimumAge;
    
    //Board game classification
    private ArrayList<Integer> types;
    private ArrayList<Integer> categories;
    private ArrayList<Integer> mechanics;
    
    //User generated information
    private Float userScore;

    public BoardGame(){};

    public BoardGame(String name, String designer, String artist, String publisher, String gameDescription, LocalDate releaseDate, Integer playingTimeMin, Integer playingTimeMax, Integer playerNoMin, Integer playerNOMax, Integer minimumAge, ArrayList<Integer> types, ArrayList<Integer> categories, ArrayList<Integer> mechanics, Float userScore) {
        this.name = name;
        this.designer = designer;
        this.artist = artist;
        this.publisher = publisher;
        this.gameDescription = gameDescription;
        this.releaseDate = releaseDate;
        this.playingTimeMin = playingTimeMin;
        this.playingTimeMax = playingTimeMax;
        this.playerNoMin = playerNoMin;
        this.playerNOMax = playerNOMax;
        this.minimumAge = minimumAge;
        this.types = types;
        this.categories = categories;
        this.mechanics = mechanics;
        this.userScore = userScore;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.name, this.releaseDate);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardGame game = (BoardGame) o;
        return Objects.equals(id, game.id) && Objects.equals(name, game.name);
    }
}


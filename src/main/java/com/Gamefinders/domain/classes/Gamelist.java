package com.Gamefinders.domain.classes;

//Imports Beginning

//Utilities
import java.util.ArrayList;

//MongoDB
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Lombok
import lombok.Getter;
import lombok.Setter;

//Imports Ending


@Setter
@Document("gamelists")
public class Gamelist {
    
    //Collection metadata
    @Id
    private String id;
    
    //User generated Content
    private String name;
    private ArrayList<BoardGame> contents;

    public Gamelist(){}

    public Gamelist(String id, String name){
        this.id = id;
        this.name = name;
        this.contents = new ArrayList<BoardGame>();
    }
}

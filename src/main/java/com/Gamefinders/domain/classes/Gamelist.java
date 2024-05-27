package com.Gamefinders.domain.classes;

//Imports Beginning

//Utilities
import java.util.ArrayList;

//MongoDB
import org.springframework.data.annotation.Id;

//Lombok
import lombok.Getter;
import lombok.Setter;

//Imports Ending

@Getter
@Setter
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

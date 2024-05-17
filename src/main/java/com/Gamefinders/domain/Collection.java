package com.Gamefinders.domain;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("Collection")
public class Collection {
    
    //Collection metadata
    @Id
    private String id;
    private String name;
    
    //User generated Content
    private ArrayList<BoardGame> contents;

    public Collection(){}

    public Collection(String id, String name, ArrayList<BoardGame> contents){
        this.id = id;
        this.name = name;
        this.contents = contents;
    }
}

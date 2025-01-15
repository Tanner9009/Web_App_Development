package com.Gamefinders.domain.classes;

//Imports Beginning

//MongoDB
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Lombok
import lombok.Getter;
import lombok.Setter;

//Imports Ending

@Getter
@Setter
@Document("reviews")
public class Review {
    
    //Review Metadata
    @Id
    public String id;
    public String authorUsername;
    private String gameId;

    //User-generated content
    public String text;

    public Review(){}

    public Review(String id, String authorId, String text, String gameId){
        this.id = id;
        this.authorUsername = authorId;
        this.text = text;
        this.gameId = gameId;
    }
}

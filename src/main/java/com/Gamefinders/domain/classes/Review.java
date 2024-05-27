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

    //User-generated content
    public String text;
    public Float userScore;

    public Review(){}

    public Review(String id, String text, Float userScore){
        this.id = id;
        this.text = text;
        this.userScore = userScore;
    }
}

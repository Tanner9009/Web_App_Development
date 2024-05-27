package com.Gamefinders.domain.classes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document("guides")
public class Guide {

    //Guide metadata
    @Id
    public String id;
    public String ownerId;
    public String gameId;

    //User generated content
    public String title;
    public String content;

    public Guide(){}

    public Guide(String id, String ownerId, String gameId, String title, String content){
        this.id = id;
        this.ownerId = ownerId;
        this.gameId = gameId;
        this.title = title;
        this.content = content;
    }
}

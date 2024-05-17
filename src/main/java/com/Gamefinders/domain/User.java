package com.Gamefinders.domain;

import java.time.LocalDate;

//Imports beginning

//Utilities
import java.util.ArrayList;

//MongoDB
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//Lombok
import lombok.Getter;
import lombok.Setter;

//Imports Ending

@Getter
@Setter
@Document("Users")
public class User {

    //User metadata
    @Id
    private String id;
    private LocalDate signupDate;

    //User information
    private String username;
    private String password;
    private String email;

    //About the user
    private ArrayList<Integer> favouriteGames;
    private ArrayList<Integer> favouriteGenres;
    private String userBio;

    public User(){}

    public User(String id, LocalDate signupDate, String username, String password, String email, ArrayList<Integer> favouriteGames, ArrayList<Integer> favouriteGenres, String userBio) {
        this.id = id;
        this.signupDate = signupDate;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favouriteGames = favouriteGames;
        this.favouriteGenres = favouriteGenres;
        this.userBio = userBio;
    }

}

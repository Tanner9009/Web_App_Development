package com.Gamefinders.domain.classes;

//Imports beginning

//Utilities
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
//MongoDB
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Gamefinders.domain.enums.Role;

//Lombok
import lombok.Getter;
import lombok.Setter;

//Imports Ending
@Getter
@Setter
@Document("users")
public class User implements UserDetails{

    //User metadata
    @MongoId
    private ObjectId id;
    private LocalDateTime registrationTimestamp;

    //User information
    private String username;
    private String password;
    private String email;
    private List<Role> roles = new ArrayList<>();

    //About the user
    private ArrayList<Integer> favouriteGames;
    private ArrayList<Integer> favouriteGenres;
    private String userBio;
    private ArrayList<User> friendList;
    private String profilePictureUrl;

    //Collections & Wishlist
    private ArrayList<BoardGame> collections = new ArrayList<>();
    private ArrayList<BoardGame> wishlist = new ArrayList<>();

    //User reviews
    private ArrayList<Review> reviews = new ArrayList<>();

    public User(){}

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.collections = new ArrayList<>();
        this.wishlist = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public User(ObjectId id, LocalDateTime signupDate, String username, String password, String email, ArrayList<Integer> favouriteGames, ArrayList<Integer> favouriteGenres, String userBio) {
        this.id = id;
        this.registrationTimestamp = signupDate;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favouriteGames = favouriteGames;
        this.favouriteGenres = favouriteGenres;
        this.userBio = userBio;
        this.collections = new ArrayList<>();
        this.wishlist = new ArrayList<>();
        this.reviews = new ArrayList<>();
        this.friendList = new ArrayList<>();
    }

    @Override
    public int hashCode(){
        return Objects.hash(username, password);
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }
    
    public boolean isAccountNonExpired() {
        return true;
    }
    
    public boolean isAccountNonLocked() {
        return true;
    } 
    public boolean isCredentialsNonExpired() {
        return true;  
    }
    
    public boolean isEnabled() {
        return true;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles)
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

}
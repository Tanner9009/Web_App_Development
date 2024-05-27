package com.Gamefinders.domain.classes;

//Imports beginning

//Utilities
import java.time.LocalDate;
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
    private LocalDate signupDate;

    //User information
    private String username;
    private String password;
    private String email;

    //About the user
    private ArrayList<Integer> favouriteGames;
    private ArrayList<Integer> favouriteGenres;
    private String userBio;
    private ArrayList<User> friendList;

    //Collections & Wishlist
    private ArrayList<Gamelist> collections;
    private ArrayList<BoardGame> wishlist;

    public User(){}

    public User(ObjectId id, LocalDate signupDate, String username, String password, String email, ArrayList<Integer> favouriteGames, ArrayList<Integer> favouriteGenres, String userBio) {
        this.id = id;
        this.signupDate = signupDate;
        this.username = username;
        this.password = password;
        this.email = email;
        this.favouriteGames = favouriteGames;
        this.favouriteGenres = favouriteGenres;
        this.userBio = userBio;
        this.collections = new ArrayList<Gamelist>();
        this.wishlist = new ArrayList<BoardGame>();
        this.friendList = new ArrayList<User>();
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

    private List<Role> roles = new ArrayList<>();

    @Override
    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles)
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        return authorities;
    }

}

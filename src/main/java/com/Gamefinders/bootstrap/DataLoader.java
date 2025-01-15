package com.Gamefinders.bootstrap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.Gamefinders.services.interfaces.UserService;

@Component
public class DataLoader {
    
    private final UserService userService;
    private final PasswordEncoder bcrypt = new BCryptPasswordEncoder();

    public DataLoader(UserService userService){
        this.userService = userService;
    }

    /* @PostConstruct
    public void loadData(){
        User user1 = new User("user1",bcrypt.encode("user1"));
        user1.getRoles().add(Role.ROLE_USER);
    
        User user2 = new User("user2",bcrypt.encode("user2"));
        user2.getRoles().add(Role.ROLE_ADMIN);
    
        userService.save(user1);
        userService.save(user2);
    }
     */
}

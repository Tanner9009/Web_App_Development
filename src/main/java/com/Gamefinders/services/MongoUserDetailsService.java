package com.Gamefinders.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Gamefinders.repositories.UserRepository;
import com.Gamefinders.domain.classes.User;

public class MongoUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public MongoUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username);
        if(user != null)
            return user;
        throw new UsernameNotFoundException("Username" + username + " could not be found");
    }
}

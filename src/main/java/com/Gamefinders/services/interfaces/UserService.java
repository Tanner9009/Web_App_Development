package com.Gamefinders.services.interfaces;

import com.Gamefinders.domain.classes.User;
import java.util.List;

public interface UserService{
    List<User> findAll();
    User findByUsername(String username);
    void save(User user);
}
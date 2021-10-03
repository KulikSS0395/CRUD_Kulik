package com.example.crud_kulik.service;

import com.example.crud_kulik.entity.User;
import java.util.List;

public interface UserService {
    void addUser(User user);
    void updateUser(User user);
    void removeUser(long id);
    User getUserById(long id);
    List<User> getListUsers();
    User findByFirstName(String firstName);
}

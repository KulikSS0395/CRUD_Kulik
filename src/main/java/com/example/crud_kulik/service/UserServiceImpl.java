package com.example.crud_kulik.service;

import com.example.crud_kulik.entity.User;
import com.example.crud_kulik.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    public void updateUser(User user) {

        usersRepository.save(user);
    }

    @Override
    public void removeUser(long id) {
        usersRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        return usersRepository.getById(id);
    }

    @Override
    public List<User> getListUsers() {
        return usersRepository.findAll();
    }

    @Override
    public User findByFirstName(String firstName) {
        return usersRepository.findByFirstName(firstName);
    }


}

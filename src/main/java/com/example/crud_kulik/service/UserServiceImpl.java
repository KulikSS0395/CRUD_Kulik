package com.example.crud_kulik.service;

import com.example.crud_kulik.entity.User;
import com.example.crud_kulik.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
    @Transactional
    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }

    @Override
    @Transactional
    public void removeUser(long id) {
        usersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        return usersRepository.findById(id).get();
    }

    @Override
    @Transactional
    public List<User> getListUsers() {
        return usersRepository.findAll();
    }

    @Override
    @Transactional
    public User findByFirstName(String firstName) {
        return usersRepository.findByFirstName(firstName);
    }


}

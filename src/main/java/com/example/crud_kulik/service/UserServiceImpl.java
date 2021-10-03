package com.example.crud_kulik.service;

import com.example.crud_kulik.entity.User;
import com.example.crud_kulik.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void addUser(User user) {
        user.setPassword(passwordEncoder().encode(user.getPassword()));
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        return usersRepository.findByEmail(s);
        return usersRepository.findByFirstName(s);
    }

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

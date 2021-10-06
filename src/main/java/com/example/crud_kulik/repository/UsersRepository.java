package com.example.crud_kulik.repository;

import com.example.crud_kulik.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    User findUsersByEmail(String email);
    User findByFirstName(String firstName);
}

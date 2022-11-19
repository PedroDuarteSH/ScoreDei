package com.example.scoredei.models;

import com.example.data.Users;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepository  extends CrudRepository<Users, Long> {
    Users findByEmail(String email);
    Users findByUsername(String username);
}

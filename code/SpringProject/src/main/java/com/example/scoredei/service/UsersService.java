package com.example.scoredei.service;


import com.example.data.Users;
import com.example.scoredei.models.UsersRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository usersRepository;


    public static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();



    public int addUser(Users users) {
        if(users.getPassword().length() <= 4 || users.getPassword().length() >= 32){
            return 3;
        }
        users.setPassword(passwordEncoder.encode(users.getPassword()));

        Users sameEmail = usersRepository.findByEmail(users.getEmail());

        if(sameEmail != null)
            if(sameEmail != users)
                return 1;
        Users sameUserName = usersRepository.findByUsername(users.getUsername());
        if(sameUserName != null)
            if(sameUserName != users)
                return 2;
        usersRepository.save(users);
        return 0;
    }


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = usersRepository.findByEmail(username);
        if (user == null) {
            user = usersRepository.findByUsername(username);
            if(user == null)
                throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.isAdmin()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(String role) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}

package ru.max.currencyconverter.currapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.max.currencyconverter.currapp.model.entity.User;
import ru.max.currencyconverter.currapp.repository.UserRepository;

import java.util.Collections;

/**
 * Created by maxxii on 19.03.2021.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User with login: " + login + " not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword()
                , Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}

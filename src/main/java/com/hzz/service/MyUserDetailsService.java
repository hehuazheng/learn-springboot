package com.hzz.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author: hezz
 */
@Component
public class MyUserDetailsService implements UserDetailsService {
    @Value("${admin.user.name}")
    private String userName;
    @Value("${admin.user.password}")
    private String password;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(userName.equals(username)) {
            return new User(userName, password, Collections.emptyList());
        }
        throw new UsernameNotFoundException(username);
    }
}

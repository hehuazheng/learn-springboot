package com.hzz.service;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author: hezz
 */
@Service
public class MyUserDetailsService implements UserDetailsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        LOGGER.info("login user name is: {}", s);
        UserDetails ud = null;
        try {
            if ("admin".equals(s)) {
                ud = new User(s, s, Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("ADMIN")));
//                ud = new User(s, s, Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("$2a$10$/FzAMhyzAx9sx3DNj3dr5..bgvHex09NazT7asg3aWIE3/WYnXn8q")));
            } else if ("guest".equals(s)) {
                ud = new User(s, s, Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("GUEST")));
            } else {
                ud = new User(s, s, Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("anon")));
                LOGGER.error("#########anonymous user login");
            }
            return ud;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
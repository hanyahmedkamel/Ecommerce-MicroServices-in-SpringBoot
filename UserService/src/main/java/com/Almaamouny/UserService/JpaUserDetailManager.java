package com.Almaamouny.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserDetailManager implements UserDetailsService {

    @Autowired
    userRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> ouser=userRepository.findByEmail(username);
        User user=ouser.get();

        return org.springframework.security.core.userdetails.User.builder().username(user.getId().toString()).password(user.getPassWord()).authorities(user.getRole()).build();
    }
}

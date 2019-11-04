package com.voltaireu.vocabularist.security.service;

import com.voltaireu.vocabularist.security.JwtUserDetailsFactory;
import com.voltaireu.vocabularist.security.model.JwtUserDetails;
import com.voltaireu.vocabularist.user.UserRepository;
import com.voltaireu.vocabularist.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public DefaultUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException(username);
        }
        return JwtUserDetailsFactory.create(user);
    }
}

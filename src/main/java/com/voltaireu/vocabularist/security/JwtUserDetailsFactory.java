package com.voltaireu.vocabularist.security;

import com.voltaireu.vocabularist.security.model.Role;
import com.voltaireu.vocabularist.security.model.JwtUserDetails;
import com.voltaireu.vocabularist.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserDetailsFactory {

    public JwtUserDetailsFactory() {
    }

    public static JwtUserDetails create(User user) {
        return new JwtUserDetails(
                user.getId(),
                mapToGrantedAuthorities(user.getRoles()),
                user.getPassword(),
                user.getUsername(),
                user.isEnabled()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> authorities) {
        return authorities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());
    }
}

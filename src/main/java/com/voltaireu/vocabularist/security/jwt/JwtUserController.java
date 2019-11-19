package com.voltaireu.vocabularist.security.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
public class JwtUserController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtUserController(JwtUtil jwtUtil, DefaultUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/authenticated-jwt-user")
    public JwtUserDetails getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String username = jwtUtil.extractUsername(token);
        return (JwtUserDetails) userDetailsService.loadUserByUsername(username);
    }
}

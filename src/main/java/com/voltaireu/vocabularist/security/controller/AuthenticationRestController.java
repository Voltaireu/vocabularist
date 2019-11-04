package com.voltaireu.vocabularist.security.controller;

import com.voltaireu.vocabularist.security.model.JwtAuthenticationRequest;
import com.voltaireu.vocabularist.security.model.JwtAuthenticationResponse;
import com.voltaireu.vocabularist.security.service.DefaultUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.voltaireu.vocabularist.security.util.JwtUtil;

@RestController
@CrossOrigin
public class AuthenticationRestController {

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private DefaultUserDetailsService userDetailsService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, DefaultUserDetailsService userDetailsService){
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping({"/authenticate"})
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

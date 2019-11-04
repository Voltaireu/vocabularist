package com.voltaireu.vocabularist.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class SignUpRestController {

    private final UserService userService;

    public SignUpRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public void signUp(@RequestBody @Valid User user) {
        userService.register(user);
    }
}

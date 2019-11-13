package com.voltaireu.vocabularist.user;

import com.fasterxml.jackson.annotation.JsonView;
import com.voltaireu.vocabularist.other.ResourceNotFoundException;
import com.voltaireu.vocabularist.other.Views;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@RestController
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users")
    @JsonView(Views.Public.class)
    public User createUser(@RequestBody @Valid User user) {
        userService.register(user);
        Log logger = LogFactory.getLog(getClass());
        logger.warn(user.getUsername());
        return user;
    }

    @RequestMapping(value = "/users", method = RequestMethod.HEAD, params = "username")
    public void isUsernameOccupied(@RequestParam String username) {
        if(userService.isUsernameNotOccupied(username)) {
            throw new ResourceNotFoundException(User.class, "username", username);
        };
    }

    @RequestMapping(value = "/users", method = RequestMethod.HEAD, params = "email")
    public void isEmailOccupied(@Email @RequestParam String email) {
        if(userService.IsEmailNotOccupied(email)) {
            throw new ResourceNotFoundException(User.class, "email", email);
        };
    }
}

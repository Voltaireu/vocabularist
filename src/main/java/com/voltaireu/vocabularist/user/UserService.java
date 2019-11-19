package com.voltaireu.vocabularist.user;

import com.voltaireu.vocabularist.other.ResourceAlreadyExistsException;
import com.voltaireu.vocabularist.other.ResourceNotFoundException;
import com.voltaireu.vocabularist.security.authority.Role;
import com.voltaireu.vocabularist.security.authority.RoleRepository;
import com.voltaireu.vocabularist.user.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, RoleRepository roleRepository, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public User register(User user){
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            String message = "User with given email or username already exists";
            throw new ResourceAlreadyExistsException(message);
        }

        Role role = roleRepository.getByName("ROLE_USER");
        user.addRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getAuthenticatedUser() {
        String username = getUsername();
        return userRepository.findByUsername(username);
    }

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(User.class, userId));
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User getUserReference(long userId) {
        return userRepository.getOne(userId);
    }

    public boolean isUsernameNotOccupied(String username) {
        return !userRepository.existsByUsername(username);
    }

    public boolean IsEmailNotOccupied(String email) {
        return !userRepository.existsByEmail(email);
    }
}

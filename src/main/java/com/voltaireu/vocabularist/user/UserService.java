package com.voltaireu.vocabularist.user;

import com.voltaireu.vocabularist.security.model.Role;
import com.voltaireu.vocabularist.security.repository.RoleRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.voltaireu.vocabularist.security.model.RoleName.ROLE_USER;

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

    public void register(User user){
        Role role = roleRepository.findByName(ROLE_USER);

        user.addRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userRepository.save(user);

        role.addUser(user);
        roleRepository.save(role);
    }

    public String getUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getUserReference(long userId) {
        return userRepository.getById(userId);
    }

    public User getUser() {
        String username = getUsername();
        return userRepository.findByUsername(username);
    }

    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException(String.format("No user with id %d found!", userId)));
    }

    public void save(User userReference) {
        userRepository.save(userReference);
    }
}

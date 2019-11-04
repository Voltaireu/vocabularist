package com.voltaireu.vocabularist.user;

import com.voltaireu.vocabularist.security.model.Role;
import com.voltaireu.vocabularist.security.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
}

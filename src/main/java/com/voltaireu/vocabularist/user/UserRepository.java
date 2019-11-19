package com.voltaireu.vocabularist.user;

import com.voltaireu.vocabularist.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);
}

package com.voltaireu.vocabularist.security.repository;


import com.voltaireu.vocabularist.security.model.Role;
import com.voltaireu.vocabularist.security.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByName(RoleName roleUser);
}

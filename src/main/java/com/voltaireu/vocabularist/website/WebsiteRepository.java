package com.voltaireu.vocabularist.website;

import com.voltaireu.vocabularist.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsiteRepository extends JpaRepository<Website, Long> {

    List<Website> findAllByUser(User user);

    boolean existsByUserAndUrl(User user, String url);
}

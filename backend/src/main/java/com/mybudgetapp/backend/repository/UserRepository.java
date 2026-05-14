package com.mybudgetapp.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mybudgetapp.backend.entity.User;

/*findByEmail, findByGoogleId
→ Spring reads the method name and writes the SQL for you
→ no SQL needed! */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByGoogleId(String googleId);
}

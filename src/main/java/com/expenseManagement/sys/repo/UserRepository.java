package com.expenseManagement.sys.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.expenseManagement.sys.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}

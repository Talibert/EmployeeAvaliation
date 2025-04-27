package com.example.employeeperformance.repositories;

import com.example.employeeperformance.entities.User;
import com.example.employeeperformance.types.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    UserDetails findByLogin(String login);

    List<User> findByUserRole(UserRole userRole);
}

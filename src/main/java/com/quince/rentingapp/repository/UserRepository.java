package com.quince.rentingapp.repository;

import com.quince.rentingapp.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByEmail(String email);
    User findByUsername(String username);
    Boolean existsByEmail(String email);
    Boolean existsByUsername(String username);
}

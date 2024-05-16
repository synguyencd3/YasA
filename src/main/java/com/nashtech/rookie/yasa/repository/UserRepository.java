package com.nashtech.rookie.yasa.repository;

import com.nashtech.rookie.yasa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}

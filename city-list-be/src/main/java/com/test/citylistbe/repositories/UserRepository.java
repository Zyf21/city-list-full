package com.test.citylistbe.repositories;

import com.test.citylistbe.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String UserName);
}


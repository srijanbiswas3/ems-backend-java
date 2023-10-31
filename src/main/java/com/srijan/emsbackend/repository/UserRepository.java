package com.srijan.emsbackend.repository;

import com.srijan.emsbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

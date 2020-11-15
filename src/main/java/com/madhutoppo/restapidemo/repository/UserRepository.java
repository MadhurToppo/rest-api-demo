package com.madhutoppo.restapidemo.repository;

import com.madhutoppo.restapidemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

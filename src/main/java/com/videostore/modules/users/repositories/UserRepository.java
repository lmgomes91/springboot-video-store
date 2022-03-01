package com.videostore.modules.users.repositories;

import com.videostore.modules.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
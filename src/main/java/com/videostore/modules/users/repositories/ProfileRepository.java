package com.videostore.modules.users.repositories;

import com.videostore.modules.users.entities.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Page<Profile> findByName(String name, Pageable pagination);
}
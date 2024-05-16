package org.mjulikelion.likelion12th3weekhomework.repository;

import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}

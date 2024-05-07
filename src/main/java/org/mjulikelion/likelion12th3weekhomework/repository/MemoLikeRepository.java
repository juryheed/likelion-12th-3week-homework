package org.mjulikelion.likelion12th3weekhomework.repository;

import org.mjulikelion.likelion12th3weekhomework.model.MemoLike;
import org.mjulikelion.likelion12th3weekhomework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemoLikeRepository extends JpaRepository<MemoLike, UUID> {
    List<MemoLike> findAllByUser(User user);
}

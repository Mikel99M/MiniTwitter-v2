package com.example.demo.repository;

import com.example.demo.domain.Like;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByLikeGiverAndLikedPost(User user, Post post);

    void deleteByLikeGiverAndLikedPost(User user, Post post);
}

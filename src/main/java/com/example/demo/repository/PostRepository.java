package com.example.demo.repository;

import com.example.demo.domain.Post;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

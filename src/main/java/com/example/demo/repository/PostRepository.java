package com.example.demo.repository;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByAuthor(User user);
}

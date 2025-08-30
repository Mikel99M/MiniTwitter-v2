package com.example.demo.repository;

import com.example.demo.domain.Like;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}

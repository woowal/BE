package com.ddingmate.ddingmate.post.repository;

import com.ddingmate.ddingmate.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}

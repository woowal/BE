package com.ddingmate.ddingmate.comment.repository;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.comment.dto.response.CommentResponse;
import com.ddingmate.ddingmate.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
}

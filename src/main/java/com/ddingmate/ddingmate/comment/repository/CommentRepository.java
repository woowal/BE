package com.ddingmate.ddingmate.comment.repository;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteByMember(Member memberId);
    void deleteByPost(Post post);
    void deleteAllByParent(Comment targetComment);

    List<Comment> findAllByParent(Comment targetComment);
//    Optional<Comment> findById(Long id);
}

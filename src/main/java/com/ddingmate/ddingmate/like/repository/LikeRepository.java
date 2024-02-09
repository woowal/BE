package com.ddingmate.ddingmate.like.repository;

import com.ddingmate.ddingmate.like.domain.Like;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Long> {
    void deleteByMemberAndPost(Member member, Post post);
}

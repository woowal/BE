package com.ddingmate.ddingmate.mark.repository;

import com.ddingmate.ddingmate.mark.domain.Mark;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    void deleteByMemberAndPost(Member member, Post post);
    List<Mark> findAllByMember(Member member);
}

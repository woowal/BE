package com.ddingmate.ddingmate.member.repository;

import com.ddingmate.ddingmate.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}

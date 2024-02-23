package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.comment.repository.CommentRepository;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.ddingmate.ddingmate.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Console;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void updateMember(Long memberId, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberId).get();
        member.update(memberUpdateRequest);
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        commentRepository.deleteByMember(member);
        postRepository.deleteByMember(member);
        memberRepository.deleteById(memberId);
    }

    public Member retrieveMember(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void updateMemberPassword(Long memberId, MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        Member member = memberRepository.findById(memberId).get();
        if(!encoder.matches(memberPasswordUpdateRequest.getOldPassword(), member.getPassword())) {
            throw new IllegalArgumentException();
        }

        if(!memberPasswordUpdateRequest.getNewPassword().equals(memberPasswordUpdateRequest.getNewPasswordCheck())) {
            throw new IllegalArgumentException();
        }
        String newPassword = encoder.encode(memberPasswordUpdateRequest.getNewPassword());
        member.updatePassword(newPassword);
    }

    @Transactional
    public void addCategory(Long memberId, CategoryRequest categoryRequest) {
        Member member = memberRepository.findById(memberId).get();
        if(member.getCategories() == null) {
            member.addCategory(categoryRequest.getCategory());
            return;
        }
        if(member.getCategories().contains(categoryRequest.getCategory())) {
            throw new IllegalArgumentException("이미 선호하는 카테고리입니다");
        }
        member.addCategory(categoryRequest.getCategory());
    }

    @Transactional
    public void removeCategory(Long memberId, CategoryRequest categoryRequest) {
        Member member = memberRepository.findById(memberId).get();
        if (member.getCategories() == null) {
            throw new IllegalArgumentException("이미 선호하지 않는 카테고리입니다");
        }
        if(!member.getCategories().contains(categoryRequest.getCategory())) {
            throw new IllegalArgumentException("이미 선호하지 않는 카테고리입니다");
        }
        member.removeCategory(categoryRequest.getCategory());
    }

}

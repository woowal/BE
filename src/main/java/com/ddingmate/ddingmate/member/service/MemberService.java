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
import java.util.NoSuchElementException;

import static com.ddingmate.ddingmate.util.exception.ExceptionEnum.*;

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
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(NO_SUCH_MEMBER.getErrorMessage()));
        member.update(memberUpdateRequest.getName(),
                memberUpdateRequest.getIntroduction(),
                memberUpdateRequest.getStudentId(),
                memberUpdateRequest.getBirth(),
                memberUpdateRequest.getUniv(),
                memberUpdateRequest.getMajor(),
                memberUpdateRequest.getCategories());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(NO_SUCH_MEMBER.getErrorMessage()));
        commentRepository.deleteByMember(member);
        postRepository.deleteByMember(member);
        memberRepository.deleteById(memberId);
    }

    public Member retrieveMember(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(NO_SUCH_MEMBER.getErrorMessage()));
    }

    @Transactional
    public void updateMemberPassword(Long memberId, MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NoSuchElementException(NO_SUCH_MEMBER.getErrorMessage()));
        if(!encoder.matches(memberPasswordUpdateRequest.getOldPassword(), member.getPassword())) {
            throw new IllegalArgumentException(FAILED_TO_AUTH_PASSWORD.getErrorMessage());
        }

        if(!memberPasswordUpdateRequest.getNewPassword().equals(memberPasswordUpdateRequest.getNewPasswordCheck())) {
            throw new IllegalArgumentException(NOT_MATCHED_PASSWORD.getErrorMessage());
        }
        String newPassword = encoder.encode(memberPasswordUpdateRequest.getNewPassword());
        member.updatePassword(newPassword);
    }

}

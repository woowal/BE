package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberUpdateRequest.getId()).get();
        member.update(memberUpdateRequest);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Member retrieveMember(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional
    public void updateMemberPassword(MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        Member member = memberRepository.findById(memberPasswordUpdateRequest.getId()).get();
        if(!encoder.matches(memberPasswordUpdateRequest.getOldPassword(), member.getPassword())) {
            throw new IllegalArgumentException();
        }

        if(!memberPasswordUpdateRequest.getNewPassword().equals(memberPasswordUpdateRequest.getNewPasswordCheck())) {
            throw new IllegalArgumentException();
        }
        String newPassword = encoder.encode(memberPasswordUpdateRequest.getNewPassword());
        member.updatePassword(newPassword);
    }

}

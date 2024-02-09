package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void updateMember(Long id, MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(id).get();
        member.update(memberUpdateRequest);
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public MemberResponse retrieveMember(Long id) {
        return MemberResponse.from(memberRepository.findById(id).get());
    }

    @Transactional
    public void updateMemberPassword(Long id, MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        Member member = memberRepository.findById(id).get();
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

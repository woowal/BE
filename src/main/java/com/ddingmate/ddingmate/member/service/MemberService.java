package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void updateMember(MemberUpdateRequest memberUpdateRequest) {
        Member member = memberRepository.findById(memberUpdateRequest.getId()).get();
        member.update(memberUpdateRequest);
    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public Member retrieveMember(Long id) {
        return memberRepository.findById(id).get();
    }

    public void updateMemberPassword(MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        Member member = memberRepository.findById(memberPasswordUpdateRequest.getId()).get();

        if(!member.getPassword().equals(memberPasswordUpdateRequest.getOldPassword())) {
            throw new IllegalArgumentException();
        }

        if(!memberPasswordUpdateRequest.getNewPassword().equals(memberPasswordUpdateRequest.getNewPasswordCheck())) {
            throw new IllegalArgumentException();
        }

        member.updatePassword(memberPasswordUpdateRequest);
    }

}

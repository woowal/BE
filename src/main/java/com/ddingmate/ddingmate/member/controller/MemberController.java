package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PatchMapping
    public ApiResponse<Void> updateMember(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(memberUpdateRequest);
        return ApiResponse.ok();
    }

    @DeleteMapping("memberId={id}")
    public ApiResponse<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ApiResponse.ok();
    }

    @GetMapping("memberId={id}")
    public ApiResponse<MemberResponse> retrieveMember(@PathVariable Long id) {
        Member member = memberService.retrieveMember(id);
        MemberResponse memberResponse = MemberResponse.from(member);
        return ApiResponse.ok(memberResponse);
    }

    @PatchMapping("/password")
    public ApiResponse<Void> updateMemberPassword(@RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        memberService.updateMemberPassword(memberPasswordUpdateRequest);
        return ApiResponse.ok();
    }

}

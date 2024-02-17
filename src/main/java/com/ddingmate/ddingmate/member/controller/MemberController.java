package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/get-current-member")
    public Long getCurrentMember(@AuthenticationPrincipal User user) {
        return memberService.getCurrentMember(user.getUsername());
    }

    @PatchMapping("/{userId}")
    public ApiResponse<Void> updateMember(@AuthenticationPrincipal User user,
                                          @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(user.getUsername(), memberUpdateRequest);
        return ApiResponse.ok();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ApiResponse.ok();
    }

    @GetMapping("{id}")
    public ApiResponse<MemberResponse> retrieveMember(@PathVariable Long id) {
//        Member member = memberService.retrieveMember(id);
//        MemberResponse memberResponse = MemberResponse.from(member);
        return ApiResponse.ok(memberService.retrieveMember(id));
    }

    @PatchMapping("/password/{userId}")
    public ApiResponse<Void> updateMemberPassword(@PathVariable("userId") Long id,
                                                  @RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        memberService.updateMemberPassword(id, memberPasswordUpdateRequest);
        return ApiResponse.ok();
    }

}

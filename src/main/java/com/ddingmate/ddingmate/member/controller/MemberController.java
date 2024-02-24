package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.*;
import com.ddingmate.ddingmate.member.dto.response.MemberResponse;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.member.state.UserAuthorize;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@UserAuthorize
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PatchMapping()
    public ApiResponse<Void> updateMember(@AuthenticationPrincipal Long memberId,
                                          @RequestBody MemberUpdateRequest memberUpdateRequest) {
        memberService.updateMember(memberId, memberUpdateRequest);
        return ApiResponse.ok();
    }

    @DeleteMapping()
    public ApiResponse<Void> deleteMember(@AuthenticationPrincipal Long memberId) {
        memberService.deleteMember(memberId);
        return ApiResponse.ok();
    }

    @GetMapping()
    public ApiResponse<MemberResponse> retrieveMember(@AuthenticationPrincipal Long memberId) {
        Member member = memberService.retrieveMember(memberId);
        return ApiResponse.ok(MemberResponse.from(member));
    }

    @PatchMapping("/password")
    public ApiResponse<Void> updateMemberPassword(@AuthenticationPrincipal Long memberId,
                                                  @RequestBody MemberPasswordUpdateRequest memberPasswordUpdateRequest) {
        memberService.updateMemberPassword(memberId, memberPasswordUpdateRequest);

        return ApiResponse.ok();
    }

}

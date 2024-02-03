package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.CodeRequest;
import com.ddingmate.ddingmate.member.dto.request.EmailRequest;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/cerify")
    public ApiResponse<Void> checkEmail(@RequestBody EmailRequest emailRequest) throws IOException {
        memberService.checkEmail(emailRequest);
        return ApiResponse.ok();
    }

    @PostMapping("/cerifycode")
    public ApiResponse<String> checkCode(@RequestBody CodeRequest codeRequest) throws IOException {
        return ApiResponse.ok(memberService.checkCode(codeRequest));
    }

}

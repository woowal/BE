package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.CodeRequest;
import com.ddingmate.ddingmate.member.dto.request.EmailRequest;
import com.ddingmate.ddingmate.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/cerify")
    public String checkEmail(@RequestBody EmailRequest emailRequest) throws IOException {
        memberService.checkEmail(emailRequest);
        return "success";
    }

    @PostMapping("/cerifycode")
    public String checkCode(@RequestBody CodeRequest codeRequest) throws IOException {
        memberService.checkCode(codeRequest);
        return "인증 성공";
    }

}

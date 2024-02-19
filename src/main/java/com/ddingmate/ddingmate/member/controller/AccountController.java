package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.LoginRequest;
import com.ddingmate.ddingmate.member.dto.request.MemberCreateRequest;
import com.ddingmate.ddingmate.member.dto.response.EmailResponse;
import com.ddingmate.ddingmate.member.dto.response.LoginResponse;
import com.ddingmate.ddingmate.member.service.AccountService;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody MemberCreateRequest memberCreateRequest) {
        accountService.register(memberCreateRequest);
        return ApiResponse.ok();
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ApiResponse.ok(accountService.login(loginRequest));
    }

    @PostMapping("/email")
    public ApiResponse<EmailResponse> sendEmailAuth(@RequestParam("email") String email) {
        return ApiResponse.ok(accountService.sendEmailAuth(email));
    }

    @PostMapping("/email/auth")
    public ApiResponse<Boolean> checkAuth(@RequestParam("email") String email,@RequestParam("code") String code) {
        Boolean result = accountService.checkAuth(email, code);
        if(result.equals(false)) {
            throw new RuntimeException("유효하지 않은 인증코드 입니다.");
        }
        return ApiResponse.ok(accountService.checkAuth(email, code));
    }

    @GetMapping("/major")
    public ApiResponse<List<Major>> retrieveMajor() {
        return ApiResponse.ok(accountService.retrieveMajor());
    }


}

package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.LoginRequest;
import com.ddingmate.ddingmate.member.dto.request.RegisterRequest;
import com.ddingmate.ddingmate.member.dto.response.EmailResponse;
import com.ddingmate.ddingmate.member.dto.response.LoginResponse;
import com.ddingmate.ddingmate.member.service.AccountService;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ApiResponse<Void> register(@RequestBody RegisterRequest registerRequest) {
        accountService.register(registerRequest);
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
    public ApiResponse<EmailResponse> checkAuth(@RequestParam("code") String code) {
        return ApiResponse.ok(accountService.checkAuth(code));
    }

    @GetMapping("/major")
    public ApiResponse<List<Major>> retrieveMajor() {
        return ApiResponse.ok(accountService.retrieveMajor());
    }


}

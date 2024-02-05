package com.ddingmate.ddingmate.member.controller;

import com.ddingmate.ddingmate.member.dto.request.RegisterRequest;
import com.ddingmate.ddingmate.member.service.AccountService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

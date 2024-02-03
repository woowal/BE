package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.dto.request.CodeRequest;
import com.ddingmate.ddingmate.member.dto.request.EmailRequest;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.univcert.api.UnivCert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void checkEmail(EmailRequest emailRequest) throws IOException {
        UnivCert.certify(emailRequest.getKey(), emailRequest.getEmail(), emailRequest.getUnivName(), true);
    }

    public String checkCode(CodeRequest codeRequest) throws IOException {
        UnivCert.certifyCode(codeRequest.getKey(), codeRequest.getEmail(), codeRequest.getUnivName(), codeRequest.getCode());
        return "인증 성공";
    }
}

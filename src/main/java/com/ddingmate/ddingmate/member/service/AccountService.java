package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.RegisterRequest;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.ddingmate.ddingmate.util.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder;

    public void register(RegisterRequest registerRequest) {
        String encodePassword = encoder.encode(registerRequest.getPassword());
        checkEmail(registerRequest.getEmail());
        Member newMember = registerRequest.toEntity(encodePassword);
        memberRepository.save(newMember);
    }

    // TODO 명지대 이메일 확인을 위한 메서드
    private void checkEmail(String email) {

    }
}

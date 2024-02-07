package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.LoginRequest;
import com.ddingmate.ddingmate.member.dto.request.RegisterRequest;
import com.ddingmate.ddingmate.member.dto.response.LoginResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.util.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        String encodePassword = encoder.encode(registerRequest.getPassword());
        checkEmail(registerRequest.getEmail());
        Member newMember = registerRequest.toEntity(encodePassword);
        memberRepository.save(newMember);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Member targetMember = memberRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> encoder.matches(loginRequest.getPassword(), user.getPassword()))
                .orElseThrow(() -> new NoSuchElementException("해당 계정은 유효하지 않습니다."));
        String token = tokenProvider.createToken(String.format("%s:%s", targetMember.getId(), targetMember.getRole()));
        return new LoginResponse(targetMember.getName(), targetMember.getRole(), token);
    }

    // TODO 명지대 이메일 확인을 위한 메서드
    private void checkEmail(String email) {

    }

    public List<Major> retrieveMajor() {

        return Arrays.stream(Major.values())
                .collect(Collectors.toList());
    }
}

package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.LoginRequest;
import com.ddingmate.ddingmate.member.dto.request.RegisterRequest;
import com.ddingmate.ddingmate.member.dto.response.EmailResponse;
import com.ddingmate.ddingmate.member.dto.response.LoginResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.util.mail.RedisUtil;
import com.ddingmate.ddingmate.util.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final Long EXPIRE_DURATION = 60*5L;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;

    @Transactional
    public void register(RegisterRequest registerRequest) {
        String encodePassword = encoder.encode(registerRequest.getPassword());
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

    public List<Major> retrieveMajor() {
        return Arrays.stream(Major.values())
                .collect(Collectors.toList());
    }

    // TODO 명지대 이메일 확인을 위한 메서드
    public EmailResponse sendEmailAuth(String email) {
        String authKey = createCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("테스트 이메일");
        message.setText("인증번호 테스트: " + authKey);

        mailSender.send(message);
        redisUtil.setDataExpire(authKey, email, EXPIRE_DURATION);
        return EmailResponse.of(email, authKey, false);
    }

    public EmailResponse checkAuth(String code) {
        String email = redisUtil.getData(code);
        if(email == null) {
            throw new NoSuchElementException("해당 인증 번호는 유효하지 않습니다.");
        }
        return EmailResponse.of(email, code, true);
    }

    private String createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(4);

            switch (index) {
                case 0: key.append((char) ((int) random.nextInt(26) + 97)); break;
                case 1: key.append((char) ((int) random.nextInt(26) + 65)); break;
                default: key.append(random.nextInt(9));
            }
        }
        return key.toString();
    }

}

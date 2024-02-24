package com.ddingmate.ddingmate.member.service;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.dto.request.LoginRequest;
import com.ddingmate.ddingmate.member.dto.request.MemberCreateRequest;
import com.ddingmate.ddingmate.member.dto.response.EmailResponse;
import com.ddingmate.ddingmate.member.dto.response.LoginResponse;
import com.ddingmate.ddingmate.member.repository.MemberRepository;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Univ;
import com.ddingmate.ddingmate.util.security.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

import static com.ddingmate.ddingmate.util.exception.ExceptionEnum.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {

    private final String SPLIT_TOKEN = "@";
    private final String EMAIL_FORM = "mju.ac.kr";
    private final String EMAIL_SUBJECT = "테스트 이메일";
    private final String EMAIL_TEXT = "인증번호 테스트: ";
    private final String TOKEN_FORM = "%s:%s";
    private HashMap<String, String> validationToken = new HashMap<>();
    private static String key;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final BCryptPasswordEncoder encoder;
    private final JavaMailSender mailSender;

    @Transactional
    public void register(MemberCreateRequest memberCreateRequest) {
        checkPassword(memberCreateRequest.getPassword(), memberCreateRequest.getPasswordCheck());
        String encodePassword = encoder.encode(memberCreateRequest.getPassword());
        Member newMember = memberCreateRequest.toEntity(encodePassword);
        memberRepository.save(newMember);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Member targetMember = memberRepository.findByEmail(loginRequest.getEmail())
                .filter(user -> encoder.matches(loginRequest.getPassword(), user.getPassword()))
                .orElseThrow(() -> new SecurityException(WRONG_LOGIN_INFO.getErrorMessage()));
        String token = tokenProvider.createToken(String.format(TOKEN_FORM, targetMember.getId(), targetMember.getRole()));
        return new LoginResponse(targetMember.getName(), targetMember.getRole(), token);
    }

    public List<Univ> retrieveUniv() {
        return Arrays.stream(Univ.values())
                .collect(Collectors.toList());
    }

    public List<Major> retrieveMajor(Univ univ) {
        return Arrays.asList(univ.getContainMajors());
    }

    // TODO 명지대 이메일 확인을 위한 메서드
    public EmailResponse sendEmailAuth(String email) {
        validEmail(email);
        createCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(EMAIL_TEXT + key);

        this.validationToken.put(email, key);
        mailSender.send(message);
        return EmailResponse.of(email, key, false);
    }

    public Boolean checkAuth(String email, String code) {
        String validateMail = findKey(code);
        if(validateMail.equals(email)) {
            this.validationToken.remove(validateMail, code);
            return true;
        }
        return false;
    }

    private String findKey(String code) {
        for (String key : this.validationToken.keySet()) {
            if(code.equals(this.validationToken.get(key))) {
                return key;
            }
        }
        return "false";
    }

    private void createCode() {
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
        this.key = key.toString();
    }

    private void checkPassword(String password, String passwordCheck) {
        if(!(password.equals(passwordCheck))) {
            throw new RuntimeException(NOT_MATCHED_PASSWORD.getErrorMessage());
        }
    }

    private void validEmail(String email) {
        String[] parts = email.split(SPLIT_TOKEN);
        if(!parts[1].equals(EMAIL_FORM)) {
            throw new RuntimeException(WRONG_EMAIL_FORM.getErrorMessage());
        }
        if(memberRepository.existsByEmail(email)) {
            throw new RuntimeException(ALREADY_EXIST_EMAIL.getErrorMessage());
        }
    }

}

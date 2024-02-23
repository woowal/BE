//package com.ddingmate.ddingmate.member.controller;
//
//import com.ddingmate.ddingmate.member.dto.request.MemberCreateRequest;
//import com.ddingmate.ddingmate.member.repository.MemberRepository;
//import com.ddingmate.ddingmate.member.service.AccountService;
//import com.ddingmate.ddingmate.member.state.Major;
//import com.ddingmate.ddingmate.member.state.Univ;
//import com.ddingmate.ddingmate.post.state.Category;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.google.gson.Gson;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@EnableWebMvc
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class AccountControllerTest {
//
//    @Mock
//    private AccountService accountService;
//
//    @Mock
//    LocalDate localDate;
//
//    @InjectMocks
//    private AccountController accountController;
//
//    @Autowired
//    private MemberRepository memberRepository;
//
//    private MockMvc mockMvc;
//
//    @BeforeEach
//    public void init() {
//        mockMvc = MockMvcBuilders.standaloneSetup(accountController).build();
//        ObjectMapper objMapper = new ObjectMapper();
//        objMapper.registerModule(new JavaTimeModule());
//        JacksonTester.initFields(this, objMapper);
//    }
//
//    @Test
//    @DisplayName("회원가입 성공")
//    void register() throws Exception {
//        MemberCreateRequest request = createRequest();
//
//        mockMvc.perform(post("/api/account/register")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(new Gson().toJson(request)))
//                .andExpect(status().isOk())
//                .andDo(print());
//
//    }
//
//    @Test
//    void login() {
//
//    }
//
//    private MemberCreateRequest createRequest() {
//        List<Category> categoryList = new ArrayList<>();
//        categoryList.add(Category.프로그래밍);
//        localDate = LocalDate.of(2005, 1, 1);
//        return MemberCreateRequest.builder()
//                .email("test@mju.ac.kr")
//                .password("qwer")
//                .passwordCheck("qwer")
//                .name("TEST_NAME")
//                .major(Major.응용소프트웨어)
//                .studentId(60231000L)
//                .birth(localDate)
//                .introduction("TEST_INTRODUCTION")
//                .univ(Univ.ICT융합대학)
//                .categories(categoryList)
//                .build();
//    }
//}

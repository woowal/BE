package com.ddingmate.ddingmate.mark.service;

import com.ddingmate.ddingmate.mark.domain.Mark;
import com.ddingmate.ddingmate.mark.dto.request.MarkCreateRequest;
import com.ddingmate.ddingmate.mark.dto.request.MarkDeleteRequest;
import com.ddingmate.ddingmate.mark.repository.MarkRepository;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.service.MemberService;
import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MarkService {

    private final MarkRepository markRepository;
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public void createMark(MarkCreateRequest markCreateRequest) {
        Member member = memberService.findMemberById(markCreateRequest.getMemberId());
        Post post = postService.findPostById(markCreateRequest.getPostId());
        Mark mark = markCreateRequest.toEntity(member, post);

        markRepository.save(mark);
    }

    @Transactional
    public void deleteMark(MarkDeleteRequest markDeleteRequest) {
        Member member = memberService.findMemberById(markDeleteRequest.getMemberId());
        Post post = postService.findPostById(markDeleteRequest.getPostId());

        markRepository.deleteByMemberAndPost(member, post);
    }
}

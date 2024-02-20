package com.ddingmate.ddingmate.mark.service;

import com.ddingmate.ddingmate.mark.domain.Mark;
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
    public void createMark(Long memberId, Long postId) {
        Member member = memberService.retrieveMember(memberId);
        Post post = postService.retrievePost(postId);
        Mark mark = Mark.builder()
                        .member(member)
                        .post(post)
                        .build();

        markRepository.save(mark);
    }

    @Transactional
    public void deleteMark(Long memberId, Long postId) {
        Member member = memberService.retrieveMember(memberId);
        Post post = postService.retrievePost(postId);

        markRepository.deleteByMemberAndPost(member, post);
    }
}

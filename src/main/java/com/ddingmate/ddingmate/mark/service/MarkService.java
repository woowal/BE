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

import static com.ddingmate.ddingmate.util.exception.ExceptionEnum.ALREADY_EXIST_MARK;

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

        boolean exist = markRepository.existsByMemberAndPost(member, post);

        if(exist) {
            throw new RuntimeException(ALREADY_EXIST_MARK.getErrorMessage());
        }
        Mark mark = new Mark(member, post);

        markRepository.save(mark);
    }

    @Transactional
    public void deleteMark(Long memberId, Long postId) {
        Member member = memberService.retrieveMember(memberId);
        Post post = postService.retrievePost(postId);

        markRepository.deleteByMemberAndPost(member, post);
    }
}

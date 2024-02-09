package com.ddingmate.ddingmate.like.service;

import com.ddingmate.ddingmate.like.domain.Like;
import com.ddingmate.ddingmate.like.dto.request.LikeCreateRequest;
import com.ddingmate.ddingmate.like.dto.request.LikeDeleteRequest;
import com.ddingmate.ddingmate.like.repository.LikeRepository;
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
public class LikeService {

    private final LikeRepository likeRepository;
    private final MemberService memberService;
    private final PostService postService;

    @Transactional
    public void createLike(LikeCreateRequest likeCreateRequest) {
        Member member = memberService.findMemberById(likeCreateRequest.getMemberId());
        Post post = postService.findPostById(likeCreateRequest.getPostId());
        Like like = likeCreateRequest.toEntity(member, post);

        likeRepository.save(like);
    }

    @Transactional
    public void deleteLike(LikeDeleteRequest likeDeleteRequest) {
        Member member = memberService.findMemberById(likeDeleteRequest.getMemberId());
        Post post = postService.findPostById(likeDeleteRequest.getPostId());

        likeRepository.deleteByMemberAndPost(member, post);
    }
}

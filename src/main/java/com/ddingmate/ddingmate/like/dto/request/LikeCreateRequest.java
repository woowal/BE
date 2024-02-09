package com.ddingmate.ddingmate.like.dto.request;

import com.ddingmate.ddingmate.like.domain.Like;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import lombok.Getter;

@Getter
public class LikeCreateRequest {

    private Long memberId;
    private Long postId;

    public Like toEntity(Member member, Post post) {
        return Like.builder()
                .member(member)
                .post(post)
                .build();
    }
}

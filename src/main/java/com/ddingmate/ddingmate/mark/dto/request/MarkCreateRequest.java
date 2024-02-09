package com.ddingmate.ddingmate.mark.dto.request;

import com.ddingmate.ddingmate.mark.domain.Mark;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import lombok.Getter;

@Getter
public class MarkCreateRequest {

    private Long memberId;
    private Long postId;

    public Mark toEntity(Member member, Post post) {
        return Mark.builder()
                .member(member)
                .post(post)
                .build();
    }
}

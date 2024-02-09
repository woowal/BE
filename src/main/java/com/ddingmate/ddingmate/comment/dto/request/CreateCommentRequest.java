package com.ddingmate.ddingmate.comment.dto.request;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotNull
    private Long postId;
    @NotNull
    private Long memberId;
    @NotEmpty
    private String content;

    public Comment toEntity(Member member, Post post) {
        return Comment.builder()
                .post(post)
                .member(member)
                .content(this.content)
                .build();

    }
}

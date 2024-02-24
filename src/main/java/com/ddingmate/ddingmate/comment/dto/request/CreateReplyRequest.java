package com.ddingmate.ddingmate.comment.dto.request;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.member.domain.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateReplyRequest {

    @NotBlank
    private String content;

    public Comment toEntity(Member writer, Comment parent) {
        return Comment.builder()
                .member(writer)
                .post(parent.getPost())
                .content(this.content)
                .parent(parent)
                .build();
    }

}

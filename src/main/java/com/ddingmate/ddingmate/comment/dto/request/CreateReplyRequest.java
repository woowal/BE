package com.ddingmate.ddingmate.comment.dto.request;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateReplyRequest {

    @NotNull
    @Column(name = "writer")
    private Long id;

    @NotBlank
    private String content;

    public Comment toEntity(Member writer, Comment parent) {
        return Comment.builder()
                .member(writer)
                .post(parent.getPost())
                .content(this.content)
                .build();
    }

}

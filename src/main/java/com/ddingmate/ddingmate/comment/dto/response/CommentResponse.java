package com.ddingmate.ddingmate.comment.dto.response;

import com.ddingmate.ddingmate.comment.domain.Comment;
import com.ddingmate.ddingmate.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponse {

    private Long commentId;
    private String content;
    private String writer;
    private boolean isMine;
    private boolean isDeleted;

    public static CommentResponse from(Comment comment, boolean isMine) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getMember().getName())
                .isMine(isMine)
                .isDeleted(comment.isDeleted())
                .build();
    }

}

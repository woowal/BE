package com.ddingmate.ddingmate.comment.dto.response;

import com.ddingmate.ddingmate.comment.domain.Comment;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Builder
public class CommentResponse {

    private Long commentId;
    private String content;
    private String writer;
    private Timestamp created;


    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .commentId(comment.getId())
                .content(comment.getContent())
                .writer(comment.getMember().getName())
                .created(comment.getCreateTime())
                .build();
    }
}

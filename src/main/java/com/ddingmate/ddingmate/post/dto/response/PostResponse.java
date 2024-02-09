package com.ddingmate.ddingmate.post.dto.response;

import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostResponse {
    private Long postId;
    private String memberName;
    private String title;
    private String content;
    private Category category;
    private Type type;
    private LocalDate dueDate;
    private int number;
    private String link;

    public PostResponse(Long postId, String memberName, String title, String content, Category category, Type type, LocalDate dueDate, int number, String link) {
        this.postId = postId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.category = category;
        this.type = type;
        this.dueDate = dueDate;
        this.number = number;
        this.link = link;
    }

    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getMember().getName(),
                post.getTitle(),
                post.getContent(),
                post.getCategory(),
                post.getType(),
                post.getDueDate(),
                post.getNumber(),
                post.getLink());
    }
}

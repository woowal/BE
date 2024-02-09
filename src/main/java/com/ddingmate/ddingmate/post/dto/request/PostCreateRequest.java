package com.ddingmate.ddingmate.post.dto.request;

import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

public class PostCreateRequest {
    private String title;
    private String content;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    @Enumerated(value = EnumType.STRING)
    private Type type;
    private LocalDate dueDate;
    private int number;
    private String link;

    public Post toEntity() {
        return Post.builder()
                .title(this.title)
                .content(this.content)
                .category(this.category)
                .type(this.type)
                .dueDate(this.dueDate)
                .number(this.number)
                .link(this.link)
                .build();
    }
}

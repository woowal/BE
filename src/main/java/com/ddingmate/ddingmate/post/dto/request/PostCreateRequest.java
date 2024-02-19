package com.ddingmate.ddingmate.post.dto.request;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDate;

@Getter
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

    public Post toEntity(Member member) {
        return Post.builder()
                .member(member)
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

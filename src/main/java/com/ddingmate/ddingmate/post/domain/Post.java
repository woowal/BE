package com.ddingmate.ddingmate.post.domain;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.dto.request.PostUpdateRequest;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import com.ddingmate.ddingmate.post.util.CategoryConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String title;
    private String content;
    @Convert(converter = CategoryConverter.class)
    private List<Category> categories;
    private Type type;
    private LocalDate dueDate;
    private int number;
    private String link;
    private LocalDate created;

    @Builder
    public Post(Member member, String title, String content, List<Category> categories, Type type, LocalDate dueDate, int number, String link, LocalDate created) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.categories = categories;
        this.type = type;
        this.dueDate = dueDate;
        this.number = number;
        this.link = link;
        this.created = LocalDate.now();
    }

    public void update(String title, String content, List<Category> categories, Type type, LocalDate dueDate, int number, String link) {
        this.title = title;
        this.content = content;
        this.categories = categories;
        this.type = type;
        this.dueDate = dueDate;
        this.number = number;
        this.link = link;
    }
}

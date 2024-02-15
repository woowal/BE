package com.ddingmate.ddingmate.post.domain;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.dto.request.PostUpdateRequest;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;
    private String title;
    private String content;
    private Category category;
    private Type type;
    private LocalDate dueDate;
    private int number;
    private String link;
    private LocalDate created;

    @Builder
    public Post(Member member, String title, String content, Category category, Type type, LocalDate dueDate, int number, String link, LocalDate created) {
        this.member = member;
        this.title = title;
        this.content = content;
        this.category = category;
        this.type = type;
        this.dueDate = dueDate;
        this.number = number;
        this.link = link;
        this.created = created;
    }

    public void update(PostUpdateRequest postUpdateRequest) {
        this.title = postUpdateRequest.getTitle();;
        this.content = postUpdateRequest.getContent();;
        this.category = postUpdateRequest.getCategory();
        this.type = postUpdateRequest.getType();
        this.dueDate = postUpdateRequest.getDueDate();
        this.number = postUpdateRequest.getNumber();
        this.link = postUpdateRequest.getLink();
    }
}

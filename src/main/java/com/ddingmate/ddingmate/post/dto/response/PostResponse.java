package com.ddingmate.ddingmate.post.dto.response;

import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PostResponse {
    private Long postId;
    private String memberName;
    private String title;
    private String content;
    private List<Category> categories;
    private Type type;
    private LocalDate dueDate;
    private int number;
    private String link;
    private boolean isMine;

    public PostResponse(Long postId, String memberName, String title, String content, List<Category> categories, Type type, LocalDate dueDate, int number, String link, boolean isMine) {
        this.postId = postId;
        this.memberName = memberName;
        this.title = title;
        this.content = content;
        this.categories = categories;
        this.type = type;
        this.dueDate = dueDate;
        this.number = number;
        this.link = link;
        this.isMine = isMine;
    }

    public static PostResponse from(Post post, boolean isMine) {
        return new PostResponse(
                post.getId(),
                post.getMember().getName(),
                post.getTitle(),
                post.getContent(),
                post.getCategories(),
                post.getType(),
                post.getDueDate(),
                post.getNumber(),
                post.getLink(),
                isMine);
    }
}

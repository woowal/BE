package com.ddingmate.ddingmate.post.dto.request;

import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class PostUpdateRequest {
    Long postId;
    String title;
    String content;
    Category category;
    Type type;
    LocalDate dueDate;
    int number;
    String link;

}

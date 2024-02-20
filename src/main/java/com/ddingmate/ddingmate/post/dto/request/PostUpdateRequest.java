package com.ddingmate.ddingmate.post.dto.request;

import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.state.Type;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class PostUpdateRequest {
    String title;
    String content;
    List<Category> categories;
    Type type;
    LocalDate dueDate;
    int number;
    String link;

}

package com.ddingmate.ddingmate.post.dto.request;

import com.ddingmate.ddingmate.post.state.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class PostCategoryRequest {

    private List<Category> categories;
}

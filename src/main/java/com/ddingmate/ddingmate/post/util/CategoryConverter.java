package com.ddingmate.ddingmate.post.util;

import com.ddingmate.ddingmate.post.state.Category;
import jakarta.persistence.AttributeConverter;

public class CategoryConverter implements AttributeConverter<Category, String> {

    @Override
    public String convertToDatabaseColumn(Category attribute) {
        return attribute.getValue();
    }

    @Override
    public Category convertToEntityAttribute(String dbData) {
        return Category.fromCode(dbData);
    }
}

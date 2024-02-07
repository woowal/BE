package com.ddingmate.ddingmate.member.util;

import com.ddingmate.ddingmate.member.state.Major;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;

@Convert
public class MajorConverter implements AttributeConverter<Major, String> {

    @Override
    public String convertToDatabaseColumn(Major attribute) {
        return attribute.getValue();
    }

    @Override
    public Major convertToEntityAttribute(String dbData) {
        return Major.fromCode(dbData);
    }
}

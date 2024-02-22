package com.ddingmate.ddingmate.member.util;

import com.ddingmate.ddingmate.member.state.Univ;
import jakarta.persistence.AttributeConverter;

public class UnivConverter implements AttributeConverter<Univ, String> {

    @Override
    public String convertToDatabaseColumn(Univ attribute) {
        return attribute.getValue();
    }

    @Override
    public Univ convertToEntityAttribute(String dbData) {
        return Univ.fromCode(dbData);
    }
}

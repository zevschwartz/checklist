package com.example.checklist.db.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by czrif on 11/8/2016.
 */
@Converter
public class BooleanToDBConverter implements AttributeConverter<Boolean, String> {
    @Override
    public String convertToDatabaseColumn(Boolean attribute) {
        if (Boolean.TRUE.equals(attribute)) {
            return "T";
        } else {
            return "F";
        }
    }

    @Override
    public Boolean convertToEntityAttribute(String dbData) {
        return "T".equals(dbData);
    }
}

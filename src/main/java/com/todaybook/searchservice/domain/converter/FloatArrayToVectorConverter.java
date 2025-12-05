package com.todaybook.searchservice.domain.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Converter
public class FloatArrayToVectorConverter implements AttributeConverter<float[], String> {

  @Override
  public String convertToDatabaseColumn(float[] attribute) {
    if (attribute == null) return null;

    String joined =
        IntStream.range(0, attribute.length)
            .mapToObj(i -> String.valueOf(attribute[i]))
            .collect(Collectors.joining(","));

    return "[" + joined + "]";
  }

  @Override
  public float[] convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isBlank()) return null;

    String[] parts = dbData.replace("[", "").replace("]", "").split(",");
    float[] result = new float[parts.length];

    for (int i = 0; i < parts.length; i++) {
      result[i] = Float.parseFloat(parts[i].trim());
    }
    return result;
  }
}

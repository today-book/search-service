package com.todaybook.searchservice.domain.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todaybook.searchservice.domain.vo.Metadata;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Converter
@RequiredArgsConstructor
public class MetadataConverter implements AttributeConverter<Metadata, String> {

  private final ObjectMapper objectMapper;

  @Override
  public String convertToDatabaseColumn(Metadata attribute) {
    if (attribute == null) {
      return null;
    }
    try {
      return objectMapper.writeValueAsString(attribute);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Failed to convert Metadata to JSON", e);
    }
  }

  @Override
  public Metadata convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isBlank()) {
      return null;
    }
    try {
      return objectMapper.readValue(dbData, Metadata.class);
    } catch (JsonProcessingException e) {
      throw new IllegalStateException("Failed to convert JSON to Metadata", e);
    }
  }
}

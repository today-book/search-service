package com.todaybook.searchservice.infrastructure;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public final class DocumentMetadataUtils {

  private DocumentMetadataUtils() {}

  public static UUID parseUUID(String id) {
    if (id == null || id.isBlank()) {
      return null;
    }
    try {
      return UUID.fromString(id);
    } catch (IllegalArgumentException ex) {
      return null;
    }
  }

  public static String getString(Map<String, Object> metadata, String key) {
    Object value = metadata.get(key);
    return value instanceof String str ? str : null;
  }

  public static List<String> getStringList(Map<String, Object> metadata, String key) {
    Object value = metadata.get(key);

    if (value instanceof List<?> list) {
      return list.stream().map(Object::toString).toList();
    }

    return Collections.emptyList();
  }

  public static LocalDateTime getDateTime(Map<String, Object> metadata, String key) {
    Object value = metadata.get(key);

    if (value instanceof LocalDateTime ldt) {
      return ldt;
    }
    return null;
  }
}

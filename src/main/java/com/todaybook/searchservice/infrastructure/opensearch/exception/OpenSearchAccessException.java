package com.todaybook.searchservice.infrastructure.opensearch.exception;

public class OpenSearchAccessException extends RuntimeException {

  public OpenSearchAccessException(String message, Throwable cause) {
    super(message, cause);
  }
}

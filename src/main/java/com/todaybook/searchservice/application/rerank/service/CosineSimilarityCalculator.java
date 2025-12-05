package com.todaybook.searchservice.application.rerank.service;

public final class CosineSimilarityCalculator {

  private CosineSimilarityCalculator() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static double calculate(float[] v1, float[] v2) {
    if (v1 == null || v2 == null) {
      throw new IllegalArgumentException("Vectors cannot be null");
    }

    if (v1.length != v2.length) {
      throw new IllegalArgumentException("Vectors must have the same length");
    }

    double dot = 0, norm1 = 0, norm2 = 0;

    for (int i = 0; i < v1.length; i++) {
      dot += v1[i] * v2[i];
      norm1 += v1[i] * v1[i];
      norm2 += v2[i] * v2[i];
    }
    double denominator = Math.sqrt(norm1) * Math.sqrt(norm2);
    if (denominator == 0.0) {
      return 0.0;
    }
    return dot / denominator;
  }
}

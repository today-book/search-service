package com.todaybook.searchservice.domain;

import com.todaybook.searchservice.domain.vo.EmotionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmotionEmbeddingRepository extends JpaRepository<EmotionEmbedding, EmotionType> {}

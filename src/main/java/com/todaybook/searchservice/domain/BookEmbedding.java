package com.todaybook.searchservice.domain;

import com.todaybook.searchservice.domain.converter.FloatArrayToVectorConverter;
import com.todaybook.searchservice.domain.converter.MetadataConverter;
import com.todaybook.searchservice.domain.vo.Metadata;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Getter;

@Getter
@Entity
@Table(name = "p_book_embeddings")
public class BookEmbedding {

  @Id private UUID id;

  private String content;

  @Convert(converter = MetadataConverter.class)
  private Metadata metadata;

  @Convert(converter = FloatArrayToVectorConverter.class)
  @Column(columnDefinition = "vector(3072)")
  private float[] embedding;
}

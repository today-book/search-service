package com.todaybook.searchservice.domain;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookEmbeddingRepository extends JpaRepository<BookEmbedding, UUID> {

  List<BookEmbedding> findAllByIdIn(Collection<UUID> ids);
}

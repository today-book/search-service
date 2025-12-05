-- ============================================
-- pgvector extension
-- ============================================
CREATE
    EXTENSION IF NOT EXISTS vector;
CREATE
    EXTENSION IF NOT EXISTS hstore;
CREATE
    EXTENSION IF NOT EXISTS "uuid-ossp";

-- ============================================
-- p_book_embeddings (책 임베딩 벡터 테이블)
-- ============================================
CREATE TABLE IF NOT EXISTS p_book_embeddings
(
    id         UUID      DEFAULT uuid_generate_v4() PRIMARY KEY,
    content    TEXT,
    metadata   JSON,
    embedding  VECTOR(3072),
--     book_id    UUID,
--     book_title      VARCHAR(255),
--     categories TEXT[]    DEFAULT '{}',
    created_at TIMESTAMP DEFAULT NOW()
);

CREATE INDEX ON p_book_embeddings USING HNSW (embedding vector_cosine_ops);

package io.include9it.java_url_shortener.db;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenerRepository extends JpaRepository<ShortenerStorageEntity, Long> {
}

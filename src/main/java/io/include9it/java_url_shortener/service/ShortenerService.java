package io.include9it.java_url_shortener.service;

import io.include9it.java_url_shortener.dto.ShortenerRequest;
import io.include9it.java_url_shortener.db.ShortenerRepository;
import io.include9it.java_url_shortener.db.ShortenerStorageEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class ShortenerService {

    private final ShortenerRepository repository;

    public String generateShortUrl(ShortenerRequest request, HttpServletRequest httpRequest) {
        ShortenerStorageEntity entity = saveSourceUrl(request);

        return httpRequest.getRequestURL() + "/" + Long.toHexString(entity.getId());
    }

    public ResponseEntity<Void> redirect(String shortenerHexId) {
        String redirectUrl = getSourceUrl(shortenerHexId);

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create(redirectUrl))
                .build();
    }

    private ShortenerStorageEntity saveSourceUrl(ShortenerRequest request) {
        var entity = new ShortenerStorageEntity();
        entity.setSourceUrl(request.sourceUrl());

        return repository.save(entity);
    }

    private String getSourceUrl(String shortenerHexId) {
        Long shortenerId = Long.parseLong(shortenerHexId, 16);

        ShortenerStorageEntity entity = repository.findById(shortenerId)
                .orElseThrow(() -> new IllegalStateException(
                        "Shortener id: " + shortenerHexId + " doesn't exist!"
                ));

        return entity.getSourceUrl();
    }
}

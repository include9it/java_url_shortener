package io.include9it.java_url_shortener.api;

import io.include9it.java_url_shortener.dto.ShortenerRequest;
import io.include9it.java_url_shortener.service.ShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shortener")
@RequiredArgsConstructor
public class ShortenerController {

    private final ShortenerService service;

    @PostMapping
    public String submit(@RequestBody ShortenerRequest request, HttpServletRequest httpRequest) {
        return service.generateShortUrl(request, httpRequest);
    }

    @GetMapping(value = "{shortenerId}")
    public ResponseEntity<Void> redirect(@PathVariable @Pattern(regexp = "^[0-9A-Fa-f]+$") String shortenerId) {
        return service.redirect(shortenerId);
    }
}

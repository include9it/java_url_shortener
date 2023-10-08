package io.include9it.java_url_shortener.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ShortenerRequest(
        @NotEmpty
        @Pattern(regexp = "^(https?://[\\S]+)|([\\S]+)$", message = "must be a valid url")
        String sourceUrl
) {
}

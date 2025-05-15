package io.tacsio.publication.controller.request;

import jakarta.validation.constraints.NotBlank;

public record PublicationRequest(
        @NotBlank String title,
        String imageUrl,
        @NotBlank String text) {
}

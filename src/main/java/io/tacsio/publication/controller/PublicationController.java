package io.tacsio.publication.controller;

import io.tacsio.publication.controller.request.PublicationRequest;
import io.tacsio.publication.domain.Publication;
import io.tacsio.publication.mapper.PublicationMapper;
import io.tacsio.publication.service.PublicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/publications")
@RequiredArgsConstructor
public class PublicationController {

    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void insert(@RequestBody @Valid PublicationRequest request) {
        var publication = publicationMapper.to(request);
        publicationService.insert(publication);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Publication> findAll() {
        return publicationService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Publication findById(@PathVariable String id) {
        return publicationService.findById(id)
                                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                     "Publication not found"));
    }
}


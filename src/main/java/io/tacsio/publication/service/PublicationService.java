package io.tacsio.publication.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.tacsio.publication.client.CommentClient;
import io.tacsio.publication.domain.Publication;
import io.tacsio.publication.mapper.PublicationMapper;
import io.tacsio.publication.repository.PublicationRepository;
import io.tacsio.publication.repository.entity.PublicationEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;
    private final CommentClient commentClient;

    public void insert(Publication publication) {
        var entity = publicationMapper.to(publication);
        publicationRepository.insert(entity);
    }

    public List<Publication> findAll() {
        return publicationRepository.findAll().stream()
            .map(publicationMapper::to)
            .toList();
    }

    @CircuitBreaker(name = "commentsApi")
    public Optional<Publication> findById(String id) {
        return publicationRepository.findById(id)
            .map(publicationMapper::to)
            .map(this::loadComments);
    }

    private Publication loadComments(Publication publication) {
        var comments = commentClient.getComments(publication.getId());
        publication.setComments(comments);

        return publication;
    }
}

package io.tacsio.publication.service;

import io.tacsio.publication.domain.Publication;
import io.tacsio.publication.mapper.PublicationMapper;
import io.tacsio.publication.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublicationService {

    private final PublicationRepository publicationRepository;
    private final PublicationMapper publicationMapper;
    private final CommentService commentService;

    public void insert(Publication publication) {
        var entity = publicationMapper.to(publication);
        publicationRepository.insert(entity);
    }

    public List<Publication> findAll() {
        return publicationRepository.findAll().stream()
            .map(publicationMapper::to)
            .toList();
    }


    public Optional<Publication> findById(String id) {
        return publicationRepository.findById(id)
            .map(publicationMapper::to)
            .map(this::loadComments);
    }


    private Publication loadComments(Publication publication) {
        var comments = commentService.getComments(publication.getId());
        publication.setComments(comments);

        return publication;
    }
}

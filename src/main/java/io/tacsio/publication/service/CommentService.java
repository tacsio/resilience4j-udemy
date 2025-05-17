package io.tacsio.publication.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.tacsio.publication.client.CommentClient;
import io.tacsio.publication.domain.Comment;
import io.tacsio.publication.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentClient commentClient;
    private final RedisRepository redisRepository;

    @CircuitBreaker(name = "commentsApi", fallbackMethod = "getCommentsFallback")
    public List<Comment> getComments(String publicationId) {
        var comments = commentClient.getComments(publicationId);
        redisRepository.save(publicationId, comments);

        return comments;
    }

    private List<Comment> getCommentsFallback(String id, Throwable cause) {
        log.warn("Fallback id: {}.  {}", id, cause.getClass().getName());
        return redisRepository.findById(id);
    }
}

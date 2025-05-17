package io.tacsio.publication.repository;

import io.tacsio.publication.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private static final String COMMENT_KEY = "comment";

    private final RedisTemplate<String, Object> redisTemplate;

    public List<Comment> save(String publicationId, List<Comment> comments) {

        redisTemplate.opsForHash().put(COMMENT_KEY, publicationId, comments);
        return comments;
    }

    public List<Comment> findById(String publicationId) {
        return (List<Comment>) redisTemplate.opsForHash().get(COMMENT_KEY, publicationId);
    }
}

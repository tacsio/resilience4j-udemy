package io.tacsio.publication.repository.entity;

import io.tacsio.publication.domain.Comment;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Data
@Document(collection = "publication")
public class PublicationEntity {

    @MongoId
    private String id;
    private String title;
    private String imageUrl;
    private String text;
    private List<Comment> comments;
}

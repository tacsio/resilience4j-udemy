package io.tacsio.publication.mapper;

import io.tacsio.publication.controller.request.PublicationRequest;
import io.tacsio.publication.domain.Publication;
import io.tacsio.publication.repository.entity.PublicationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PublicationMapper {

    PublicationEntity to(Publication publication);

    Publication to(PublicationEntity publicationEntity);

    Publication to(PublicationRequest publicationRequest);
}

package com.alexis.tarotapp.api.mappers;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;
import com.alexis.tarotapp.api.dto.listing.ReadingSessionListingResultDto;
import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.repository.listing.ReadingSessionListingResult;
import org.hibernate.Session;
import org.mapstruct.Mapper;

@Mapper
public interface IReadingSessionMapper {
    ReadingSession dtoToEntity(Session session, ReadingSessionDto readingSessionDto);
    ReadingSessionDto entityToDto(ReadingSession readingSession);

    ReadingSessionListingResultDto entityToDto(ReadingSessionListingResult readingSessionListingResult);
}

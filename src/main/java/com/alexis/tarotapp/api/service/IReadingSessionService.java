package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;
import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.ReadingSessionListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;

public interface IReadingSessionService {
    Result<ReadingSession> add(ReadingSessionDto readingSessionDto);
    Result<ReadingSession> update(ReadingSessionDto readingSessionDto);
    Result<Boolean> delete(int id);
    Result<ReadingSessionListingResult> list(PaginationParams paginationParams);
    Result<ReadingSession> fetch(int id);
}

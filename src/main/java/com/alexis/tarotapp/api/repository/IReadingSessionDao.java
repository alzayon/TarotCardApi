package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.ReadingSessionListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.Session;

public interface IReadingSessionDao {
    Result<ReadingSession> add(Session session, ReadingSession readingSession);

    Result<ReadingSession> update(Session session, ReadingSession readingSession);

    Result<ReadingSessionListingResult> list(Session session, PaginationParams paginationParams);
    Result<ReadingSession> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

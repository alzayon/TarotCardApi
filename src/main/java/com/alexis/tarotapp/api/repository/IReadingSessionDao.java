package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.Session;

import java.util.List;

public interface IReadingSessionDao {
    Result<ReadingSession> add(Session session, ReadingSession readingSession);

    Result<ReadingSession> update(Session session, ReadingSession readingSession);

    Result<List<ReadingSession>> list(Session session);
    Result<ReadingSession> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Reading;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.Session;

import java.util.List;

public interface IReadingDao {
    Result<Reading> add(Session session, Reading reading);

    Result<Reading> update(Session session, Reading reading);

    Result<List<Reading>> list(Session session);
    Result<Reading> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

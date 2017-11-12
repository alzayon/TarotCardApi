package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;
import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface IReadingSessionService {
    Result<ReadingSession> add(ReadingSessionDto readingSessionDto);
    Result<ReadingSession> update(ReadingSessionDto readingSessionDto);
    Result<Boolean> delete(int id);
    Result<List<ReadingSession>> list();
    Result<ReadingSession> fetch(int id);
}

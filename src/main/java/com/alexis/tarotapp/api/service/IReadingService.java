package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.ReadingDto;
import com.alexis.tarotapp.api.entities.Reading;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface IReadingService {
    Result<Reading> add(ReadingDto readingDto);
    Result<Reading> update(ReadingDto readingDto);
    Result<Boolean> delete(int id);
    Result<List<Reading>> list();
    Result<Reading> fetch(int id);
}

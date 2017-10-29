package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.MeaningDto;
import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface IMeaningService {
    Result<Meaning> add(MeaningDto meaningDto);
    Result<Meaning> update(MeaningDto meaningDto);
    Result<Boolean> delete(int id);
    Result<List<Meaning>> list();
    Result<Meaning> fetch(int id);
}

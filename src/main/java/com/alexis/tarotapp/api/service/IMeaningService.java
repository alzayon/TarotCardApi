package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface IMeaningService {
    Result<Meaning> add(Meaning meaning);
    Result<Boolean> delete(Meaning meaning);
    Result<List<Meaning>> list();
    Result<Meaning> fetch(int id);
}

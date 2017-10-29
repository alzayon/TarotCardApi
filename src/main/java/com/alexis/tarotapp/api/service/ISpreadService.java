package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.SpreadDto;
import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface ISpreadService {
    Result<Spread> add(SpreadDto cardDto);
    Result<Spread> update(SpreadDto cardDto);
    Result<Boolean> delete(int id);
    Result<List<Spread>> list();
    Result<Spread> fetch(int id);
}

package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.SpreadComponentDto;
import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.general.result.Result;

import java.util.List;

public interface ISpreadComponentService {
    Result<SpreadComponent> add(SpreadComponentDto spreadComponentDto);
    Result<SpreadComponent> update(SpreadComponentDto spreadComponentDto);
    Result<Boolean> delete(int id);
    Result<List<SpreadComponent>> list();
    Result<SpreadComponent> fetch(int id);
}

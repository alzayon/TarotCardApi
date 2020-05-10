package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.SpreadDto;
import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.SpreadListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;

public interface ISpreadService {
    Result<Spread> add(SpreadDto cardDto);
    Result<Spread> update(SpreadDto cardDto);
    Result<Boolean> delete(int id);
    Result<SpreadListingResult> list(PaginationParams paginationParams);
    Result<Spread> fetch(int id);
}

package com.alexis.tarotapp.api.service;

import com.alexis.tarotapp.api.dto.MeaningDto;
import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.MeaningListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;

public interface IMeaningService {
    Result<Meaning> add(MeaningDto meaningDto);
    Result<Meaning> update(MeaningDto meaningDto);
    Result<Boolean> delete(int id);
    Result<MeaningListingResult> list(PaginationParams paginationParams);
    Result<Meaning> fetch(int id);
}

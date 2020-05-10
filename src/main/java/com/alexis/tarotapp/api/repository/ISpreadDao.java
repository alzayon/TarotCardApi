package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.SpreadListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.Session;

public interface ISpreadDao {
    Result<Spread> add(Session session, Spread spread);

    Result<Spread> update(Session session, Spread spread);

    Result<SpreadListingResult> list(Session session, PaginationParams paginationParams);
    Result<Spread> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

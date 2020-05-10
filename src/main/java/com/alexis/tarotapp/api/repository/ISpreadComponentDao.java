package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.SpreadComponentListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.Session;

import java.util.List;

public interface ISpreadComponentDao {
    Result<SpreadComponent> add(Session session, SpreadComponent spreadComponent);

    Result<SpreadComponent> update(Session session, SpreadComponent spreadComponent);

    Result<SpreadComponentListingResult> list(Session session, PaginationParams paginationParams);
    Result<SpreadComponent> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Meaning;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.MeaningListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by alzayon on 8/2/2017.
 */
public interface IMeaningDao {
    Result<Meaning> add(Session session, Meaning meaning);

    Result<Meaning> update(Session session, Meaning meaning);

    Result<MeaningListingResult> list(Session session, PaginationParams paginationParams);
    Result<Meaning> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

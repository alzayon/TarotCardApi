package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.Session;

import java.util.List;

public interface ISpreadDao {
    Result<Spread> add(Session session, Spread spread);

    Result<Spread> update(Session session, Spread spread);

    Result<List<Spread>> list(Session session);
    Result<Spread> fetch(Session session, int id);

    Result<Boolean> delete(Session session, int id);
}

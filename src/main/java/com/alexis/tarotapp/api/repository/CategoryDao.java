package com.alexis.tarotapp.api.repository;

import com.alexis.tarotapp.api.entities.Category;
import com.alexis.tarotapp.api.general.result.Result;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by alzayon on 8/1/2017.
 */
public class CategoryDao implements ICategoryDao {

    @Override
    public Result<Category> add(Session session, Category category) {
        session.save(category);
        return new Result<>(category);
    }


    @Override
    public Result<Category> update(Session session, Category category) {
        final Category reattachedCategory = (Category)session.merge(category);
        session.save(reattachedCategory);
        return new Result<>(reattachedCategory);
    }

    @Override
    public Result<List<Category>> list(Session session) {
        final Query query = session.createQuery("from Category");
        final List<Category> categories = query.list();
        return new Result<>(categories);
    }

    @Override
    public Result<Category> fetch(Session session, int id) {
        //https://stackoverflow.com/questions/11089599/hibernate-get-entity-by-id
        final Category category = (Category) session.get(Category.class, id);
        return new Result<>(category);
    }

    @Override
    public Result<Boolean> delete(Session session, int id) {
        //http://www.codejava.net/frameworks/hibernate/hibernate-basics-3-ways-to-delete-an-entity-from-the-datastore
        //https://www.mkyong.com/hibernate/hibernate-cascade-example-save-update-delete-and-delete-orphan/
        final Category category = (Category) session.get(Category.class, id);
        boolean outcome = false;
        if (category != null) {
            session.delete(category);
            outcome = true;
        }
        return new Result<>(outcome);
    }
}

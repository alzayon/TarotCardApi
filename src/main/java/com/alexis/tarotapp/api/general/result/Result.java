package com.alexis.tarotapp.api.general.result;

import java.util.Optional;

/**
 * Created by alzayon on 6/28/2017.
 */
public class Result<T> {
    private final T item;

    private Throwable error;

    public Result(T item) {
        this.item = item;
    }

    public Result(T item, Throwable error) {
        this.item = item;
        this.error = error;
    }

    public T getItem() {
        return item;
    }

    public boolean empty() {
        return item == null;
    }

    public Optional<Throwable> getError() {
        final Optional<Throwable> opt = Optional.ofNullable(this.error);
        return opt;
    }
}

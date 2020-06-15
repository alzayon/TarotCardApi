package com.alexis.tarotapp.api.entities;

/**
 * Created by alzayon on 7/19/2017.
 */
public class ReadingSession {

    private long id;
    private String description;

    public ReadingSession() {}

    public ReadingSession(long id,
                          String description) {
        this.id = id;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}

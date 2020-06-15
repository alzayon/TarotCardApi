package com.alexis.tarotapp.api.entities;

import javax.persistence.*;

/**
 * Created by alzayon on 6/16/2017.
 */
public class Reading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //http://viralpatel.net/blogs/hibernate-one-to-many-annotation-tutorial/
    @ManyToOne
    @JoinColumn(name = "meaning_id")
    private Meaning meaning;

    //http://viralpatel.net/blogs/hibernate-one-to-many-annotation-tutorial/
    @ManyToOne
    @JoinColumn(name = "spread_component_id")
    private SpreadComponent spreadComponent;

    public Reading() {}

    public Reading(long id, Meaning meaning, SpreadComponent spreadComponent) {
        this.id = id;
        this.meaning = meaning;
        this.spreadComponent = spreadComponent;
    }

    public long getId() {
        return id;
    }

    public Meaning getMeaning() {
        return meaning;
    }

    public SpreadComponent getSpreadComponent() {
        return spreadComponent;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMeaning(Meaning meaning) {
        this.meaning = meaning;
    }

    public void setSpreadComponent(SpreadComponent spreadComponent) {
        this.spreadComponent = spreadComponent;
    }
}

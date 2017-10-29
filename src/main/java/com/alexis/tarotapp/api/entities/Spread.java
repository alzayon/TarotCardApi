package com.alexis.tarotapp.api.entities;

import javax.persistence.*;

/**
 * Created by alzayon on 6/16/2017.
 */
@Entity
@Table(name = "spreads")
public class Spread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    public Spread() {
    }

    public Spread(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.alexis.tarotapp.api.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alzayon on 7/18/2017.
 */
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Meaning> meanings;

    public Category() {
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

    public List<Meaning> getMeanings() {
        return meanings;
    }
}

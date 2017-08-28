package com.alexis.tarotapp.api.entities;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alzayon on 6/16/2017.
 */
@Entity
@Table(name = "cards")
public class Card {

    public Card() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int type;

    //https://stackoverflow.com/questions/16511237/how-to-use-hibernate-annotations-manytoone-and-onetomany-for-associations
    @OneToMany(mappedBy = "card")
    private List<Meaning> meanings;


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}

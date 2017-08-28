package com.alexis.tarotapp.api.dto;

/**
 * Created by alzayon on 8/20/2017.
 */
public class CardDto {

    private int id;

    private String name;

    private int type;

    public CardDto(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}

package com.alexis.tarotapp.api.dto;

/**
 * Created by alzayon on 8/20/2017.
 */
public class CategoryDto {
    private int id;

    private String name;

    public CategoryDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

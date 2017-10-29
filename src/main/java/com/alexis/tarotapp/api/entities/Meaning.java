package com.alexis.tarotapp.api.entities;

import javax.persistence.*;

/**
 * Created by alzayon on 7/18/2017.
 */
@Entity
@Table(name = "meanings")
public class Meaning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //http://viralpatel.net/blogs/hibernate-one-to-many-annotation-tutorial/
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "meaning_text")
    private String meaningText;

    public Meaning() {
    }

    public Meaning(int id, Card card, Category category, String meaningText) {
        this.id = id;
        this.card = card;
        this.category = category;
        this.meaningText = meaningText;
    }

    public int getId() {
        return id;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMeaningText() {
        return meaningText;
    }

    public void setMeaningText(String meaningText) {
        this.meaningText = meaningText;
    }
}

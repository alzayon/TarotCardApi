package com.alexis.tarotapp.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "spread_components")
public class SpreadComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int position;

    @ManyToOne
    @JoinColumn(name = "spread_id")
    private Spread spread;

    public SpreadComponent() {}

    public SpreadComponent(long id, int position, Spread spread) {
        this.id = id;
        this.position = position;
        this.spread = spread;
    }

    public long getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public Spread getSpread() {
        return spread;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setSpread(Spread spread) {
        this.spread = spread;
    }
}

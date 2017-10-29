package com.alexis.tarotapp.api.entities;

import javax.persistence.*;

@Entity
@Table(name = "spread_components")
public class SpreadComponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final long id;

    @Column
    private final int position;

    @ManyToOne
    @JoinColumn(name = "spread_id")
    private final Spread spread;


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
}

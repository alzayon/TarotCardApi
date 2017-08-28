package com.alexis.tarotapp.api.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by alzayon on 7/19/2017.
 */
public class TarotSession {

    private int id;

    private Date date;

    private Spread spread;

    private List<Reading> readings;
}

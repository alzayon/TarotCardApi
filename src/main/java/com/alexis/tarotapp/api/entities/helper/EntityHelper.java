package com.alexis.tarotapp.api.entities.helper;

import com.alexis.tarotapp.api.dto.*;
import com.alexis.tarotapp.api.entities.*;
import org.hibernate.Session;

public class EntityHelper {

    public static Card toEntity(Session session, CardDto cardDto) {
        final Card card = new Card(cardDto.getId(),
                                    cardDto.getName(),
                                cardDto.getType());
        return card;
    }

    public static Category toEntity(Session session, CategoryDto categoryDto) {
        final Category category = new Category(categoryDto.getId(),
                                               categoryDto.getName());
        return category;
    }

    public static Meaning toEntity(Session session, MeaningDto meaningDto) {
        final Card card = (Card) session.get(Card.class,
                                             meaningDto.getCard().getId());
        final Category category = (Category) session.get(Category.class,
                                                         meaningDto.getCategory().getId());
        final Meaning meaning = new Meaning(meaningDto.getId(),
                                            card,
                                            category,
                                            meaningDto.getMeaningText()
        );
        return meaning;
    }

    public static Spread toEntity(Session session, SpreadDto spreadDto) {
        final Spread spread = new Spread(spreadDto.getId(),
                spreadDto.getName());
        return spread;
    }

    public static SpreadComponent toEntity(Session session, SpreadComponentDto spreadComponentDto) {
        final Spread spread = (Spread) session.get(Spread.class,
                                                   spreadComponentDto.getSpread().getId());
        final SpreadComponent spreadComponent = new SpreadComponent(spreadComponentDto.getId(),
                spreadComponentDto.getPosition(),
                spread);
        return spreadComponent;
    }

    public static Reading toEntity(Session session, ReadingDto readingDto) {
        final Meaning meaning = toEntity(session, readingDto.getMeaning());
        final SpreadComponent spreadComponent = (SpreadComponent) session.get(SpreadComponent.class,
                                                                      readingDto.getSpreadComponent().getId());
        final Reading reading = new Reading(readingDto.getId(),
                                            meaning,
                                            spreadComponent);
        return reading;
    }



}

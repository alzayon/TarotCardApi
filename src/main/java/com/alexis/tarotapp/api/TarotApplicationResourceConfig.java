package com.alexis.tarotapp.api;

import com.alexis.tarotapp.api.service.ICardService;
import com.alexis.tarotapp.api.service.ICategoryService;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by alzayon on 6/28/2017.
 */
public class TarotApplicationResourceConfig extends ResourceConfig {
    TarotApplicationResourceConfig(final ICardService cardService,
                                   final ICategoryService categoryService) {
        packages("com.alexis.tarotapp.api");

        final JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        register(jsonProvider);

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(cardService).to(ICardService.class);
                bind(categoryService).to(ICategoryService.class);
            }
        });
    }
}

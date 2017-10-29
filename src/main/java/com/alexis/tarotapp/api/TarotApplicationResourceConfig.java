package com.alexis.tarotapp.api;

import com.alexis.tarotapp.api.general.provider.JsonMappingExceptionMapper;
import com.alexis.tarotapp.api.service.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by alzayon on 6/28/2017.
 */
public class TarotApplicationResourceConfig extends ResourceConfig {
    TarotApplicationResourceConfig(final ICardService cardService,
                                   final ICategoryService categoryService,
                                   final IMeaningService meaningService,
                                   final ISpreadService spreadService,
                                   final ISpreadComponentService spreadComponentService,
                                   final IReadingService readingService) {
        packages("com.alexis.tarotapp.api");

        //https://stackoverflow.com/questions/2332515/how-to-get-jersey-logs-at-server
        //https://groups.google.com/forum/#!topic/dropwizard-user/ILk5XGmXpPA
        register(new LoggingFeature(Logger.getLogger(LoggingFeature.DEFAULT_LOGGER_NAME),
                Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY,
                Integer.MAX_VALUE));

        final JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        //https://stackoverflow.com/questions/28231323/jersey-jackson-how-to-catch-json-mapping-exception
        final JsonMappingExceptionMapper jsonMappingExceptionMapper = new JsonMappingExceptionMapper();

        register(jsonProvider);
        register(jsonMappingExceptionMapper);

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(cardService).to(ICardService.class);
                bind(categoryService).to(ICategoryService.class);
                bind(meaningService).to(IMeaningService.class);
                bind(spreadService).to(ISpreadService.class);
                bind(spreadComponentService).to(ISpreadComponentService.class);
                bind(readingService).to(IReadingService.class);
            }
        });
    }
}

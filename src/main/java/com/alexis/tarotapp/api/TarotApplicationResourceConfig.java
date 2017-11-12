package com.alexis.tarotapp.api;

import com.alexis.tarotapp.api.service.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
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
                Level.INFO,
                LoggingFeature.Verbosity.PAYLOAD_ANY,
                1000)); //https://stackoverflow.com/questions/42037644/jersey-2-x-throws-negativearraysizeexception-when-try-to-logging-request-for-no

        final JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        register(jsonProvider);


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

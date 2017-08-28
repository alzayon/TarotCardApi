package com.alexis.tarotapp.api;

import com.alexis.tarotapp.api.repository.CardDao;
import com.alexis.tarotapp.api.repository.CategoryDao;
import com.alexis.tarotapp.api.repository.ICardDao;
import com.alexis.tarotapp.api.repository.ICategoryDao;
import com.alexis.tarotapp.api.service.CardService;
import com.alexis.tarotapp.api.service.CategoryService;
import com.alexis.tarotapp.api.service.ICardService;
import com.alexis.tarotapp.api.service.ICategoryService;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/myapp/";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.alexis.tarotapp.api package
        final ICardDao cardDao = new CardDao();
        final ICategoryDao categoryDao = new CategoryDao();
        final ICardService cardService = new CardService(cardDao);
        final ICategoryService categoryService = new CategoryService(categoryDao);
        final ResourceConfig rc = new TarotApplicationResourceConfig(cardService, categoryService);

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }

}


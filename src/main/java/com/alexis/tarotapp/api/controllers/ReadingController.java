package com.alexis.tarotapp.api.controllers;

import com.alexis.tarotapp.api.entities.Reading;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

/**
 * Created by alzayon on 6/18/2017.
 */
@Path("readingresource")
public class ReadingController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Reading> list() {
        return null;
    }


}

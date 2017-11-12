package com.alexis.tarotapp.api.controllers;

import com.alexis.tarotapp.api.dto.ReadingSessionDto;
import com.alexis.tarotapp.api.dto.helper.DtoHelper;
import com.alexis.tarotapp.api.entities.ReadingSession;
import com.alexis.tarotapp.api.general.patch.PATCH;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.service.IReadingSessionService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Path("readingsessionresource")
public class ReadingSessionController {
    @Context
    Request request;

    @Context
    IReadingSessionService readingSessionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ReadingSessionDto readingSessionDto) {
        //https://stackoverflow.com/questions/23858488/how-i-return-http-404-json-xml-response-in-jax-rs-jersey-on-tomcat
        final Result<ReadingSession> result = readingSessionService.add(readingSessionDto);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final int id, final ReadingSessionDto ReadingSessionDto) {
        if (ReadingSessionDto.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Error", "The spread id does not match the resource path")
                    .build();
        }

        final Result<ReadingSession> resultReadingSession = readingSessionService.fetch(id);

        if (!resultReadingSession.empty()) {
            final Result<ReadingSession> result = readingSessionService.update(ReadingSessionDto);
            return Response.ok(DtoHelper.toDto(result.getItem())).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("{id}")
    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response patch(@PathParam("id") final int id, final JsonPatch document) {
        final Result<ReadingSession> resultReadingSession = readingSessionService.fetch(id);

        if (!resultReadingSession.empty()) {
            final ReadingSession spreadInstance = resultReadingSession.getItem();
            final ObjectMapper objectMapper = new ObjectMapper();
            try {
                if (document == null) {
                    throw new IllegalArgumentException("Json patch document is null!");
                }

                final String json = objectMapper.writeValueAsString(spreadInstance);

                //https://stackoverflow.com/questions/3653996/how-to-parse-a-json-string-into-jsonnode-in-jackson
                final JsonNode jsonNode = objectMapper.readTree(json);

                //https://github.com/java-json-tools/json-patch
                final JsonNode appliedJsonNode = document.apply(jsonNode);

                //https://stackoverflow.com/questions/19711695/convert-jsonnode-into-pojo
                final ReadingSessionDto ReadingSessionDto = objectMapper.treeToValue(appliedJsonNode, ReadingSessionDto.class);

                final Result<ReadingSession> result = readingSessionService.update(ReadingSessionDto);

                return Response.ok(DtoHelper.toDto(result.getItem())).build();

            } catch (IOException | JsonPatchException ex) {
                throw new RuntimeException(ex);
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") final int id) {
        //https://stackoverflow.com/questions/2342579/http-status-code-for-update-and-delete
        //http://allegro.tech/2014/10/async-rest.html
        final Result<ReadingSession> resultReadingSession = readingSessionService.fetch(id);

        if (!resultReadingSession.empty()) {
            final Result<Boolean> result = readingSessionService.delete(id);
            if (result.getItem()) {
                return Response.noContent().build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return Response.noContent().build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() {
        final Result<List<ReadingSession>> result = readingSessionService.list();
        final List<ReadingSessionDto> spreads = result.getItem().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return Response.ok(spreads).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        final Result<ReadingSession> result = readingSessionService.fetch(id);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }
}

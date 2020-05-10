package com.alexis.tarotapp.api.controllers;

import com.alexis.tarotapp.api.dto.ReadingDto;
import com.alexis.tarotapp.api.dto.helper.DtoHelper;
import com.alexis.tarotapp.api.dto.pagination.PaginationDto;
import com.alexis.tarotapp.api.entities.Reading;
import com.alexis.tarotapp.api.general.patch.PATCH;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.ReadingListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.service.IReadingService;
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

/**
 * Created by alzayon on 6/18/2017.
 */
@Path("readingresource")
public class ReadingController {

    @Context
    Request request;

    @Context
    IReadingService readingService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ReadingDto readingDto) {
        //https://stackoverflow.com/questions/23858488/how-i-return-http-404-json-xml-response-in-jax-rs-jersey-on-tomcat
        final Result<Reading> result = readingService.add(readingDto);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, ReadingDto readingDto) {
        if (readingDto.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Error", "The reading id does not match the resource path")
                    .build();
        }

        final Result<Reading> resultReading = readingService.fetch(id);

        if (!resultReading.empty()) {
            final Result<Reading> result = readingService.update(readingDto);
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
        final Result<Reading> resultReading = readingService.fetch(id);

        if (!resultReading.empty()) {
            final Reading readingInstance = resultReading.getItem();
            final ObjectMapper objectMapper = new ObjectMapper();
            try {
                if (document == null) {
                    throw new IllegalArgumentException("Json patch document is null!");
                }

                final ReadingDto readingDtoInstance = DtoHelper.toDto(readingInstance);
                final String json = objectMapper.writeValueAsString(readingDtoInstance);

                //https://stackoverflow.com/questions/3653996/how-to-parse-a-json-string-into-jsonnode-in-jackson
                final JsonNode jsonNode = objectMapper.readTree(json);

                //https://github.com/java-json-tools/json-patch
                final JsonNode appliedJsonNode = document.apply(jsonNode);

                //https://stackoverflow.com/questions/19711695/convert-jsonnode-into-pojo
                final ReadingDto readingDto = objectMapper.treeToValue(appliedJsonNode, ReadingDto.class);

                final Result<Reading> result = readingService.update(readingDto);

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
    public Response delete(@PathParam("id") int id) {

        //https://stackoverflow.com/questions/2342579/http-status-code-for-update-and-delete
        //http://allegro.tech/2014/10/async-rest.html

        final Result<Reading> resultReading = readingService.fetch(id);

        if (!resultReading.empty()) {
            final Result<Boolean> result = readingService.delete(id);
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
    public Response get(@BeanParam PaginationDto paginationDto) {
        final Result<ReadingListingResult> result = readingService.list(
                new PaginationParams(paginationDto.start, paginationDto.limit)
        );
        if (result.getError().isPresent()) {
            throw new RuntimeException("Error selecting records");
        }

        final ReadingListingResult readingListingResult = result.getItem();
        return Response.ok(DtoHelper.toDto(readingListingResult)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        final Result<Reading> result = readingService.fetch(id);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }


}

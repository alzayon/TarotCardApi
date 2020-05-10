package com.alexis.tarotapp.api.controllers;

import com.alexis.tarotapp.api.dto.SpreadDto;
import com.alexis.tarotapp.api.dto.helper.DtoHelper;
import com.alexis.tarotapp.api.dto.listing.SpreadListingResultDto;
import com.alexis.tarotapp.api.dto.pagination.PaginationDto;
import com.alexis.tarotapp.api.entities.Spread;
import com.alexis.tarotapp.api.general.patch.PATCH;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.SpreadListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.service.ISpreadService;
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
 * Created by alzayon on 6/18/2017.
 */
@Path("spreadresource")
public class SpreadController {

    @Context
    Request request;

    @Context
    ISpreadService spreadService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(SpreadDto spreadDto) {
        //https://stackoverflow.com/questions/23858488/how-i-return-http-404-json-xml-response-in-jax-rs-jersey-on-tomcat
        final Result<Spread> result = spreadService.add(spreadDto);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final int id, final SpreadDto spreadDto) {
        if (spreadDto.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Error", "The spread id does not match the resource path")
                    .build();
        }

        final Result<Spread> resultSpread = spreadService.fetch(id);

        if (!resultSpread.empty()) {
            final Result<Spread> result = spreadService.update(spreadDto);
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
        final Result<Spread> resultSpread = spreadService.fetch(id);

        if (!resultSpread.empty()) {
            final Spread spreadInstance = resultSpread.getItem();
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
                final SpreadDto SpreadDto = objectMapper.treeToValue(appliedJsonNode, SpreadDto.class);

                final Result<Spread> result = spreadService.update(SpreadDto);

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
        final Result<Spread> resultSpread = spreadService.fetch(id);

        if (!resultSpread.empty()) {
            final Result<Boolean> result = spreadService.delete(id);
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
        final Result<SpreadListingResult> result = spreadService.list(
                new PaginationParams(paginationDto.start, paginationDto.limit)
        );
        if (result.getError().isPresent()) {
            throw new RuntimeException("Error selecting records");
        }

        final SpreadListingResult spreadListingResult = result.getItem();
        return Response.ok(DtoHelper.toDto(spreadListingResult)).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        final Result<Spread> result = spreadService.fetch(id);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }

}

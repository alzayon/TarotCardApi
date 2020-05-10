package com.alexis.tarotapp.api.controllers;

import com.alexis.tarotapp.api.dto.SpreadComponentDto;
import com.alexis.tarotapp.api.dto.helper.DtoHelper;
import com.alexis.tarotapp.api.dto.listing.SpreadComponentListingResultDto;
import com.alexis.tarotapp.api.dto.pagination.PaginationDto;
import com.alexis.tarotapp.api.entities.SpreadComponent;
import com.alexis.tarotapp.api.general.patch.PATCH;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.repository.listing.SpreadComponentListingResult;
import com.alexis.tarotapp.api.repository.pagination.PaginationParams;
import com.alexis.tarotapp.api.service.ISpreadComponentService;
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

@Path("spreadcomponentresource")
public class SpreadComponentController {
    @Context
    Request request;

    @Context
    ISpreadComponentService spreadComponentService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(SpreadComponentDto spreadComponent) {
        //https://stackoverflow.com/questions/23858488/how-i-return-http-404-json-xml-response-in-jax-rs-jersey-on-tomcat
        final Result<SpreadComponent> result = spreadComponentService.add(spreadComponent);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, SpreadComponentDto SpreadComponentDto) {
        if (SpreadComponentDto.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Error", "The spreadComponent id does not match the resource path")
                    .build();
        }

        final Result<SpreadComponent> resultSpreadComponent = spreadComponentService.fetch(id);

        if (!resultSpreadComponent.empty()) {
            final Result<SpreadComponent> result = spreadComponentService.update(SpreadComponentDto);
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
        final Result<SpreadComponent> resultSpreadComponent = spreadComponentService.fetch(id);

        if (!resultSpreadComponent.empty()) {
            final SpreadComponent spreadComponentInstance = resultSpreadComponent.getItem();
            final ObjectMapper objectMapper = new ObjectMapper();
            try {
                if (document == null) {
                    throw new IllegalArgumentException("Json patch document is null!");
                }

                final SpreadComponentDto SpreadComponentDtoInstance = DtoHelper.toDto(spreadComponentInstance);
                final String json = objectMapper.writeValueAsString(SpreadComponentDtoInstance);

                //https://stackoverflow.com/questions/3653996/how-to-parse-a-json-string-into-jsonnode-in-jackson
                final JsonNode jsonNode = objectMapper.readTree(json);

                //https://github.com/java-json-tools/json-patch
                final JsonNode appliedJsonNode = document.apply(jsonNode);

                //https://stackoverflow.com/questions/19711695/convert-jsonnode-into-pojo
                final SpreadComponentDto SpreadComponentDto = objectMapper.treeToValue(appliedJsonNode, SpreadComponentDto.class);

                final Result<SpreadComponent> result = spreadComponentService.update(SpreadComponentDto);

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

        final Result<SpreadComponent> resultSpreadComponent = spreadComponentService.fetch(id);

        if (!resultSpreadComponent.empty()) {
            final Result<Boolean> result = spreadComponentService.delete(id);
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
        final Result<SpreadComponentListingResult> result = spreadComponentService.list(
                new PaginationParams(paginationDto.start, paginationDto.limit)
        );
        if (result.getError().isPresent()) {
            throw new RuntimeException("Error selecting records");
        }

        final SpreadComponentListingResult spreadComponentListingResult = result.getItem();
        final List<SpreadComponentDto> spreadComponent = spreadComponentListingResult.getListing().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        final SpreadComponentListingResultDto spreadComponentSessionListingResultDto =
                new SpreadComponentListingResultDto(spreadComponentListingResult.getCount(), spreadComponent);

        return Response.ok(spreadComponentSessionListingResultDto).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        final Result<SpreadComponent> result = spreadComponentService.fetch(id);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }
}

package com.alexis.tarotapp.api.controllers;

import com.alexis.tarotapp.api.dto.CardDto;
import com.alexis.tarotapp.api.dto.DtoHelper;
import com.alexis.tarotapp.api.entities.Card;
import com.alexis.tarotapp.api.general.patch.PATCH;
import com.alexis.tarotapp.api.repository.ICardDao;
import com.alexis.tarotapp.api.repository.hibernate.SessionUtil;
import com.alexis.tarotapp.api.general.result.Result;
import com.alexis.tarotapp.api.service.ICardService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.glassfish.jersey.server.ManagedAsync;
import org.hibernate.Session;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alzayon on 6/16/2017.
 */
@Path("cardresource")
public class CardController {

    @Context
    Request request;

    @Context
    ICardService cardService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Card card) {
        //https://stackoverflow.com/questions/23858488/how-i-return-http-404-json-xml-response-in-jax-rs-jersey-on-tomcat
        final Result<Card> result = cardService.add(card);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }

    @Path("{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") final int id, final Card card) {
        if (card.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .header("Error", "The card id does not match the resource path")
                    .build();
        }

        final Result<Card> resultCard = cardService.fetch(id);

        if (!resultCard.empty()) {
            final Result<Card> result = cardService.update(card);
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
        final Result<Card> resultCard = cardService.fetch(id);

        if (!resultCard.empty()) {
            final Card cardInstance = resultCard.getItem();
            final ObjectMapper objectMapper = new ObjectMapper();
            try {
                if (document == null) {
                    throw new IllegalArgumentException("Json patch document is null!");
                }

                final String json = objectMapper.writeValueAsString(cardInstance);

                //https://stackoverflow.com/questions/3653996/how-to-parse-a-json-string-into-jsonnode-in-jackson
                final JsonNode jsonNode = objectMapper.readTree(json);

                //https://github.com/java-json-tools/json-patch
                final JsonNode appliedJsonNode = document.apply(jsonNode);

                //https://stackoverflow.com/questions/19711695/convert-jsonnode-into-pojo
                final Card card = objectMapper.treeToValue(appliedJsonNode, Card.class);

                final Result<Card> result = cardService.update(card);

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
        final Result<Card> resultCard = cardService.fetch(id);

        if (!resultCard.empty()) {
            final Result<Boolean> result = cardService.delete(resultCard.getItem());
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
        final Result<List<Card>> result = cardService.list();
        final List<CardDto> cards = result.getItem().stream()
                .map(DtoHelper::toDto)
                .collect(Collectors.toList());
        return Response.ok(cards).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("id") int id) {
        final Result<Card> result = cardService.fetch(id);
        return Response.ok(DtoHelper.toDto(result.getItem())).build();
    }
}

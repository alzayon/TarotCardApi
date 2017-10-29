package com.alexis.tarotapp.api.general.exceptionmapper;


import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
//https://stackoverflow.com/questions/26796363/catch-all-exceptions-and-also-return-custom-errors-in-jersey
public class CustomExceptionMapper implements ExceptionMapper<Throwable> {

    private final static Logger LOGGER = Logger.getLogger(CustomExceptionMapper.class.getName());

    public Response toResponse(Throwable error) {
        Response response;
        LOGGER.severe(error.getMessage());
        if (error instanceof WebApplicationException) {
            WebApplicationException webEx = (WebApplicationException) error;
            response = webEx.getResponse();
        } else {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal error").type("text/plain").build();
        }
        return response;
    }
}
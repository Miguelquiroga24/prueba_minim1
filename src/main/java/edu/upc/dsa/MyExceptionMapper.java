package edu.upc.dsa;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.grizzly.utils.Exceptions;

/**
 * Clase para mapear excepciones globales a respuestas HTTP.
 * Devuelve un error 500 junto con el stack trace.
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception ex) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(Exceptions.getStackTraceAsString(ex))
                .type("text/plain")
                .build();
    }
}

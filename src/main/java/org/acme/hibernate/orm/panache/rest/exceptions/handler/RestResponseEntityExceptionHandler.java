package org.acme.hibernate.orm.panache.rest.exceptions.handler;

import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.exceptions.model.ApiExceptionModel;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.time.LocalDateTime;


@Provider
public class RestResponseEntityExceptionHandler implements ExceptionMapper <RestApplicationException> {

    @Override
    public Response toResponse(RestApplicationException exception)
    {
        return Response.status(Response.Status.BAD_REQUEST).entity(new ApiExceptionModel(exception.getMessage(), LocalDateTime.now())).build();
    }
}

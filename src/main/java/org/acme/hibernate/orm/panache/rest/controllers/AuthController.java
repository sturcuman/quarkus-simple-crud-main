package org.acme.hibernate.orm.panache.rest.controllers;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.models.User;
import org.acme.hibernate.orm.panache.rest.services.TokenService;
import org.acme.hibernate.orm.panache.rest.services.UserService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;

import org.jboss.resteasy.reactive.RestForm;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

@Path("/auth")
public class AuthController {

    @Inject
    UserService userService;
    @Inject
    Template register;
    @Inject
    Template login;
    @Inject
    TokenService tokenService;


    @GET
    @Path("/register")
    @PermitAll
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getRegister(@Context SecurityContext securityContext) {
        return register.instance();
    }


    @GET
    @Path("/login")
    @PermitAll
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getLogin(@Context SecurityContext ctx) {
        return login.instance();
    }


    @POST
    @PermitAll
    @Transactional
    @Path("/register")
    public Response register(@RestForm String login, @RestForm String password) throws RestApplicationException {
        User user = userService.create(new User(login, password));

        String token = tokenService.generateToken(login);
        user.token = token;

        return Response.seeOther(URI.create("/auth/login")).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
    }


    @POST
    @PermitAll
    @Transactional
    @Path("/login")
    public Response login(@RestForm String login, @RestForm String password) throws RestApplicationException {
        User user = userService.login(login, password);

        String token = tokenService.generateToken(user.login);
        user.token = token;

//        try(FileWriter writer = new FileWriter("notes3.txt"))
//        {
//            String text = token;
//            writer.write(text);
//            writer.flush();
//        }
//        catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }

        return Response.seeOther(URI.create("/cars")).header(HttpHeaders.AUTHORIZATION, "Bearer " + token).build();
    }

}

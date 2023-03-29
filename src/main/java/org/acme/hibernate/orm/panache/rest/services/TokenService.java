package org.acme.hibernate.orm.panache.rest.services;

import io.smallrye.jwt.build.Jwt;

import javax.enterprise.context.ApplicationScoped;
import java.util.Arrays;
import java.util.HashSet;

@ApplicationScoped
public class TokenService {

    public String generateToken(String login) {
        return Jwt.issuer("https://example.com/issuer")
                .upn(login)
                .groups(new HashSet<>(Arrays.asList("User")))
                .claim("login", login)
                .sign();
    }
}

package org.acme.hibernate.orm.panache.rest.services;

import io.quarkus.elytron.security.common.BcryptUtil;
import org.acme.hibernate.orm.panache.rest.exceptions.RestApplicationException;
import org.acme.hibernate.orm.panache.rest.models.User;

import javax.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class UserService {

    public static final String ERROR_USER_NOT_FOUND = "User with id: %s not found!";
    public static final String ERROR_USER_WITH_LOGIN_NOT_FOUND = "User with login: '%s' not found!";
    public static final String ERROR_USER_ALREADY_EXISTS = "User with login: '%s' is already exists!";


    public User create(User user) throws RestApplicationException {
        System.out.println("Create user with login: " + user.login);
        if(User.findByLogin(user.login) != null) {
            throw new RestApplicationException(String.format(ERROR_USER_ALREADY_EXISTS, user.login));
        }
        user.persist();

        return user;
    }


    public User login(String login, String password) throws RestApplicationException {
        System.out.println("Login user with login: " + login);

        User user = User.findByLogin(login);
        if(user == null) {
            throw new RestApplicationException(String.format(ERROR_USER_WITH_LOGIN_NOT_FOUND, login));
        }

        if(BcryptUtil.matches(password, user.password) == false) {
            throw new RestApplicationException("Invalid password");
        }

        return user;
    }


    public User getById(Long id) throws RestApplicationException {
        User user = User.findById(id);
        if(user == null) {
            throw new RestApplicationException(String.format(ERROR_USER_NOT_FOUND, id));
        }
        return user;
    }


    public User getByLogin(String login) throws RestApplicationException {
        User user = User.findByLogin(login);
        if(user == null) {
            throw new RestApplicationException(String.format(ERROR_USER_WITH_LOGIN_NOT_FOUND, login));
        }

        return user;
    }


    public void deleteById(Long id) throws RestApplicationException {
        User user = User.findById(id);
        if(user == null) {
            throw new RestApplicationException(String.format(ERROR_USER_NOT_FOUND, id));
        }

        User.deleteById(id);
    }
}

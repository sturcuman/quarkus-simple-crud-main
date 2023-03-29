package org.acme.hibernate.orm.panache.rest.dao;

import org.acme.hibernate.orm.panache.rest.models.User;

public class UserDAO {

    public Long id;
    public String login;
    public String token;

    public UserDAO(User user) {
        id = user.id;
        login = user.login;
        token = user.token;
    }
}

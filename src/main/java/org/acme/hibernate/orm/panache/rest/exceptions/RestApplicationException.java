package org.acme.hibernate.orm.panache.rest.exceptions;

import java.io.Serializable;

public class RestApplicationException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L;

    public RestApplicationException(String msg) {
        super(msg);
    }
}

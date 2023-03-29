package org.acme.hibernate.orm.panache.rest.exceptions.model;


import java.time.LocalDateTime;



public class ApiExceptionModel {


    private String message;
    private LocalDateTime time;


    public ApiExceptionModel() {

    }


    public ApiExceptionModel(String message, LocalDateTime time) {
        this.message = message;
        this.time = time;
    }


    public String getMessage() {
        return message;
    }


    public LocalDateTime getTime() {
        return time;
    }

}

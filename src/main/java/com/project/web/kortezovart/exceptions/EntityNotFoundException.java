package com.project.web.kortezovart.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String attribute, String parameter, int value ) {
        super(String.format("No such %s with  %s %d", attribute, parameter, value));
    }
    public EntityNotFoundException(String attribute,String parameter, String value) {
        super(String.format("No such %s with  %s %s", attribute, parameter, value));
    }
}

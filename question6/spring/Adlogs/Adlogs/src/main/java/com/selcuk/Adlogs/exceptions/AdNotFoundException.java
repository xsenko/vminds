package com.selcuk.Adlogs.exceptions;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException(String id) {
        super("Could not find ad id with userid:" + id);
    }
}

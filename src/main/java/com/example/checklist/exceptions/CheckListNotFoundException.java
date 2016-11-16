package com.example.checklist.exceptions;

/**
 * Created by czrif on 11/13/2016.
 */
public class CheckListNotFoundException extends RuntimeException {
    String reason = "Cannot find CheckList";

    public CheckListNotFoundException() {
    }

    public CheckListNotFoundException(Long id) {
        super();
        this.reason = reason + " ID " + id;
    }

    @Override
    public String getMessage() {
        return reason;
    }
}

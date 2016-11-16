package com.example.checklist.web;

/**
 * Created by czrif on 11/10/2016.
 */
public class ResponseMessage {
    String message;
    String additionalInformation;

    public ResponseMessage() {
    }

    public ResponseMessage(String message, String additionalInformation) {
        this.message = message;
        this.additionalInformation = additionalInformation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}

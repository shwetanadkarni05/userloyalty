package com.loyalty.server.utility;

/**
 */

public enum Message {
    CREATE_SUCCESS("%1$s was created successfully")
    ,REQUIRED_KEYS_MISSING("required parameters for %1$s are missing")
    ,EXISTS_CHOOSE_ANOTHER("this %1$s exists ")
    ,NO_MATCH_FOUND("the %1$s you were looking for was not found")
    ,NOT_SUFFICIENT_BALANCE("there isn't sufficient balance to carry out this transfer")
    ,FETCH_SUCCESS("%1$s was fetched successfully");


    private String message;

    Message(String inMessage) {
        message = inMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String inMessage) {
        message = inMessage;
    }
}

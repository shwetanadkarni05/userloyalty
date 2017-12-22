package com.loyalty.shared.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.LinkedList;
import java.util.List;


/**
 */

public class UserLoyaltyError {

    private List<String> theErrors;

    public UserLoyaltyError() {
        theErrors = new LinkedList<String>();
    }

    public UserLoyaltyError(List<String> inTheErrors) {
        theErrors = inTheErrors;
    }

    public UserLoyaltyError(String inErrorMessage) {
        theErrors = new LinkedList<String>();
        if (inErrorMessage != null && !"".equals(inErrorMessage)) {
            theErrors.add(inErrorMessage);
        }
    }

    public List<String> getTheErrors() {
        return theErrors;
    }

    public void setTheErrors(List<String> inTheErrors) {
        theErrors = inTheErrors;
    }

    public void addErrorMessage(String inErrorMessage) {
        if (inErrorMessage == null || "".equals(inErrorMessage)) {
            return;
        }

        if (theErrors == null) {
            theErrors = new LinkedList<String>();
        }

        theErrors.add(inErrorMessage);
    }

    public String convertToJson() {

        if (theErrors == null) {
            theErrors = new LinkedList<String>();
        }

        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            return theObjectMapper.writeValueAsString(theErrors);
        } catch (Exception jpe) {
            return null;
        }
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ValidationErrors{");
        sb.append("theErrors=").append(theErrors);
        sb.append('}');
        return sb.toString();
    }
}

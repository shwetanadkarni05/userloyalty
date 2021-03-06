package com.loyalty.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.server.dao.UserDao;
import com.loyalty.shared.domain.User;
import com.loyalty.shared.domain.UserLoyaltyError;

import java.io.IOException;


/**
 */

public class UserController {

    public User convertUserJsonToObject(String inUserJsonString) {
        if (inUserJsonString == null || "".equals(inUserJsonString)) {
            return null;
        }

        User theUser = null;
        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            theUser = theObjectMapper.readValue(inUserJsonString, User.class);
        } catch (IOException ioe) {
            theUser = null;
        }

        return theUser;
    }

    public User createUser(User inUser)
            throws Exception {

        if (inUser == null) {
            throw new Exception("Required information was null or empty");
        }
        UserLoyaltyError theUserLoyaltyError = validateUser(inUser);
        if (theUserLoyaltyError != null && !theUserLoyaltyError.getTheErrors().isEmpty()) {
            throw new Exception(theUserLoyaltyError.getTheErrors().toString());
        }

        User theSavedUser = (new UserDao()).insertUser(inUser);

        return theSavedUser;
    }

    private UserLoyaltyError validateUser(User inUser) {

        UserLoyaltyError theUserLoyaltyError = null;

        if (inUser == null) {
            theUserLoyaltyError = new UserLoyaltyError("Null user object was received. Please check the request.");
        } else {
            theUserLoyaltyError = new UserLoyaltyError();
            if (inUser.getFirstName() == null || "".equals(inUser.getFirstName())) {
                theUserLoyaltyError.addErrorMessage("First name cannot be empty");
            }
            if (inUser.getLastName() == null || "".equals(inUser.getLastName())) {
                theUserLoyaltyError.addErrorMessage("Last name cannot be empty");
            }
        }

        //TODO Add Additional Validation code like email pattern

        return theUserLoyaltyError;
    }

    public String convertUserObjectToJson(User inUser) {

        if (inUser == null) {
            return null;
        }

        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            return theObjectMapper.writeValueAsString(inUser);
        } catch (Exception jpe) {
            return null;
        }
    }
}

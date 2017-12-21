package com.loyalty.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.server.dao.TransferDao;
import com.loyalty.shared.domain.Transfer;
import com.loyalty.shared.domain.UserLoyaltyError;

import java.io.IOException;


/**
 */

public class TransferController {

    public Transfer convertTransferJsonToObj(String inTransfer) {
        if (inTransfer == null || "".equals(inTransfer)) {
            return null;
        }

        Transfer theTransfer = null;
        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            theTransfer = theObjectMapper.readValue(inTransfer, Transfer.class);
        } catch (IOException ioe) {
            theTransfer = null;
        }

        return theTransfer;
    }

    public Transfer createTransfer(Transfer inTransfer)
            throws Exception {

        if (inTransfer == null) {
            throw new Exception("Required information was null or empty");
        }
        UserLoyaltyError theUserLoyaltyError = validateTransfer(inTransfer);
        if (theUserLoyaltyError != null && !theUserLoyaltyError.getTheErrors().isEmpty()) {
            throw new Exception(theUserLoyaltyError.toString());
        }

        Transfer theSavedTransfer = (new TransferDao()).insertTransfer(inTransfer);

        return theSavedTransfer;
    }

    private UserLoyaltyError validateTransfer(Transfer inTransfer) {

        UserLoyaltyError theUserLoyaltyError = null;

        //TODO Add validation Code

        //TODO Add Additional Validation code like email pattern


        return theUserLoyaltyError;
    }

    public String convertTransferObjectToJson(Transfer inTransfer) {

        if (inTransfer == null) {
            return null;
        }

        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            return theObjectMapper.writeValueAsString(inTransfer);
        } catch (Exception jpe) {
            return null;
        }
    }
}

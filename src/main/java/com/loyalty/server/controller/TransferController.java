package com.loyalty.server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.server.dao.TransferDao;
import com.loyalty.shared.domain.Transfer;
import com.loyalty.shared.domain.UserLoyaltyError;

import java.io.IOException;
import java.util.List;


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
            throw new Exception(theUserLoyaltyError.getTheErrors().toString());
        }

        Transfer theSavedTransfer = (new TransferDao()).insertTransfer(inTransfer);

        return theSavedTransfer;
    }

    private UserLoyaltyError validateTransfer(Transfer inTransfer) {

        UserLoyaltyError theUserLoyaltyError = null;

        if (inTransfer == null) {
            theUserLoyaltyError = new UserLoyaltyError("Null user object was received. Please check the request.");
        } else {
            theUserLoyaltyError = new UserLoyaltyError();
            if (inTransfer.getUserId() == null || inTransfer.getUserId()<=0) {
                theUserLoyaltyError.addErrorMessage("User id is invalid");
            }
            if (inTransfer.getAmount() == null) {
                theUserLoyaltyError.addErrorMessage("Transfer amount is invalid");
            }
        }

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

    public List<Transfer> getTransfers(Integer inUserId)
            throws Exception {
        if (inUserId == null || inUserId == 0) {
            return null;
        }

        return (new TransferDao()).getTransfers(inUserId);
    }

    public String convertTransferListObjectToJson(List<Transfer> inTransfers) {
        if (inTransfers == null) {
            return null;
        }

        ObjectMapper theObjectMapper = new ObjectMapper();
        try {
            return theObjectMapper.writeValueAsString(inTransfers);
        } catch (Exception jpe) {
            return null;
        }
    }
}

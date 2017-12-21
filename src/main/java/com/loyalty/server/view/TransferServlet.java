package com.loyalty.server.view;

import com.loyalty.server.controller.TransferController;
import com.loyalty.shared.domain.Transfer;
import com.loyalty.shared.domain.UserLoyaltyError;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 */
@Path ("{version}/transfer")
public class TransferServlet {

    @POST
    @Produces ("application/json" + "")
    @Consumes ("application/json" + "")
    public Response postTransaction(String inTransfer) {
        if (inTransfer == null || "".equals(inTransfer)) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new UserLoyaltyError("No transfer details were received. Could not save transaction.")).build();
        }

        Transfer theTransfer = null;
        try {
            theTransfer = (new TransferController()).convertTransferJsonToObj(inTransfer);
        } catch (Exception e) {
            theTransfer = null;
        }

        if (theTransfer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity(new UserLoyaltyError("Please check input as something went wrong while trying to read transfer details. Could not create transfer.")).build();
        }

        try {
            theTransfer = (new TransferController()).createTransfer(theTransfer);
        } catch (Exception e) {
            UserLoyaltyError theUserLoyaltyError = new UserLoyaltyError(e.getMessage());
            theUserLoyaltyError.addErrorMessage("Could not save transfer.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(theUserLoyaltyError).build();
        }


        String theTransferJson = (new TransferController()).convertTransferObjectToJson(theTransfer);
        if (theTransferJson != null) {
            return Response.status(Response.Status.OK).entity(theTransferJson).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(new UserLoyaltyError("Something went wrong internally trying to save transfer.")).build();
        }
    }
}

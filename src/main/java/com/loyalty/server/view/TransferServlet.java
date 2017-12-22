package com.loyalty.server.view;

import com.loyalty.server.controller.TransferController;
import com.loyalty.shared.domain.Transfer;
import com.loyalty.shared.domain.UserLoyaltyError;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 */
@Path ("{version}/transfer")
public class TransferServlet {

    @POST
    @Produces ("application/json" + "")
    @Consumes ("application/json" + "")
    public Response postTransaction(String inTransfer) {
        if (inTransfer == null || "".equals(inTransfer)) {
            return Response.status(Response.Status.BAD_REQUEST).entity((new UserLoyaltyError("No transfer details were received. Could not save transaction.")).convertToJson()).build();
        }

        Transfer theTransfer = null;
        try {
            theTransfer = (new TransferController()).convertTransferJsonToObj(inTransfer);
        } catch (Exception e) {
            theTransfer = null;
        }

        if (theTransfer == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity((new UserLoyaltyError("Please check input as something went wrong while trying to read transfer details. Could not create transfer.")).convertToJson()).build();
        }

        try {
            theTransfer = (new TransferController()).createTransfer(theTransfer);
        } catch (Exception e) {
            UserLoyaltyError theUserLoyaltyError = new UserLoyaltyError(e.getMessage());
            theUserLoyaltyError.addErrorMessage("could not save transfer.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(theUserLoyaltyError.convertToJson()).build();
        }


        String theTransferJson = (new TransferController()).convertTransferObjectToJson(theTransfer);
        if (theTransferJson != null) {
            return Response.status(Response.Status.OK).entity(theTransferJson).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new UserLoyaltyError("Something went wrong internally trying to save transfer.")).convertToJson()).build();
        }
    }

    @Path ("/user")
    @GET
    @Produces ("application/json" + "")
    public Response getITNCampaignsDetails(@QueryParam ("id") Integer inUserId) {

        if (inUserId == null || inUserId == 0) {
            return Response.status(Response.Status.BAD_REQUEST).entity((new UserLoyaltyError("User id parameter is a required field. Could not fetch list of transfers.")).convertToJson()).build();
        }

        List<Transfer> theTransfers = null;
        try {
            theTransfers = (new TransferController()).getTransfers(inUserId);
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity((new UserLoyaltyError(e.getMessage())).convertToJson()).build();
        }

        String theTransferJson = (new TransferController()).convertTransferListObjectToJson(theTransfers);
        if (theTransferJson != null) {
            return Response.status(Response.Status.OK).entity(theTransferJson).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new UserLoyaltyError("Something went wrong internally trying to retrieve transfer.")).convertToJson()).build();
        }
    }
}

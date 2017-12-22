package com.loyalty.server.view;

import com.loyalty.server.controller.UserController;
import com.loyalty.shared.domain.User;
import com.loyalty.shared.domain.UserLoyaltyError;

import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


/**
 */
@Path ("{version}/user")
public class UserServlet {

    @POST
    @Produces ("application/json" + "")
    @Consumes ("application/json" + "")
    @Path ("/register")
    public Response postUser(String inUserDetails) {

        if (inUserDetails == null || "".equals(inUserDetails)) {
            return Response.status(Response.Status.BAD_REQUEST).entity((new UserLoyaltyError("No user details were received. Could not register new user.")).convertToJson()).build();
        }
        User theUser = null;
        try {
            theUser = (new UserController()).convertUserJsonToObject(inUserDetails);
        }catch (Exception e){
            theUser = null;
        }

        if(theUser == null){
            return Response.status(Response.Status.BAD_REQUEST).entity((new UserLoyaltyError("Please check input as something went wrong while trying to read user details. Could not register new user.")).convertToJson()).build();
        }

        try{
            theUser = (new UserController()).createUser(theUser);
        }catch (Exception e){
            UserLoyaltyError theUserLoyaltyError = new UserLoyaltyError(e.getMessage());
            theUserLoyaltyError.addErrorMessage("could not register user.");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(theUserLoyaltyError.convertToJson()).build();
        }


        String theUser_Json = (new UserController()).convertUserObjectToJson(theUser);
        if(theUser_Json!=null){
            return Response.status(Response.Status.OK).entity(theUser_Json).build();
        }else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity((new UserLoyaltyError("Something went wrong internally.")).convertToJson()).build();
        }

    }
}

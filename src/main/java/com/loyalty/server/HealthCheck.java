package com.loyalty.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


/**
 */
@Path("isAlive")
public class HealthCheck {

    @GET
    @Produces (MediaType.TEXT_PLAIN)
    public String getMessage() {
        return "Hello world!";
    }
}

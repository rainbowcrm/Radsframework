package com.techtrade.rads.framework.test.restservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@Path("testrads")
public class TestRestService {

	@GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
}

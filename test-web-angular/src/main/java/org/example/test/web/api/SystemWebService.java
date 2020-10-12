package org.example.test.web.api;

import java.time.LocalDateTime;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/system/time")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SystemWebService {

	@GET
	public Response servertimestamp() {
		return Response.ok(LocalDateTime.now(), MediaType.APPLICATION_JSON).build();
	}
}

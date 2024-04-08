package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;

import com.google.gson.Gson;

@Path("/register") 
public class RegisterResource {
	
	private static final Logger LOG = Logger.getLogger(LoginResource.class.getName());
	
	public RegisterResource(){}
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response doRregistration(RegisterData data) {

	}
}

package pt.unl.fct.di.apdc.firstwebapp.resources;

import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.gson.Gson;

import com.google.appengine.repackaged.org.apache.commons.codec.digest.DigestUtils;

import pt.unl.fct.di.apdc.firstwebapp.util.AuthToken;
import pt.unl.fct.di.apdc.firstwebapp.util.LoginData;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
public class LoginResource {

	private static final Logger LOG = Logger.getLogger(LoginResource.class.getName());

	Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
	KeyFactory userKeyFactory = datastore.newKeyFactory().setKind("username");
	
	private final Gson g = new Gson();

	public LoginResource() {
	}

	@POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response doLogin(LoginData data) {
		
		LOG.fine("Login attempt by user: " + data.username);
		
		Key userKey = userKeyFactory.newKey(data.username);
		Entity user = datastore.get(userKey);
		if(user != null) {
			String hashedPWD = (String) user.getString("user_pwd");
			if(hashedPWD.equals(DigestUtils.sha512Hex(data.password))) {
				user = Entity.newBuilder(user)
						.set("user_login_time", Timestamp.now())
						.build();
				datastore.update(user);
				LOG.info("User '" + data.username + "' logged in sucessfully.");
				AuthToken token = new AuthToken(data.username);
				return Response.ok(g.toJson(token)).build();
		}else {
			LOG.warning("Wrong password for: " + data.username);
			return Response.status(Status.FORBIDDEN).build();
		}
		}else {
			LOG.warning("Failed login attempt for username: " + data.username);
			return Response.status(Status.FORBIDDEN).build();		
		}
    }
}

package tn.esprit.IRMC.resources;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.resource.spi.work.SecurityContext;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("authentication")
@Transactional
public class AuthentificationEndPoint {

	// ======================================
	// = Injection Points =
	// ======================================

	@Context
	private UriInfo uriInfo;

	
	@Context
	SecurityContext securityContext;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	//consommer un formulaire et retourner le token en format text_plain 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	//@Path("{username}/{password}")
	public Response authenticateUser(@FormParam("username") String username, @FormParam("password") String password) {
		try {
			
			// Authenticate the user using the credentials provided
			authenticate(username, password);

			// Issue a token for the user
			String token = issueToken(username);

			// Return the token on the response
			return Response.ok(token).build();

		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}

	private void authenticate(String username, String password) {
		// Authenticate against a database, LDAP, file or whatever
		// Throw an Exception if the credentials are invalid
		System.out.println("Authenticating user...");
	
	}

	private String issueToken(String username) {
		// Issue a token (can be a random String persisted to a database or a JWT token)
		// The issued token must be associated to a user
		// Return the issued token

		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("the key is : " + key);

		String jwtToken = Jwts.builder().setSubject(username).
				setIssuer(uriInfo.getAbsolutePath().toString())
				//duree de session plusMinute (15L) 
				.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
				.signWith(SignatureAlgorithm.HS512, key).compact();

		System.out.println("the returned token is : " + jwtToken);
		return jwtToken;
	}

	// ======================================
	// = Private methods =
	// ======================================

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}


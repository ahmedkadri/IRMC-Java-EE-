package tn.esprit.IRMC.resources;


import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.MembreServiceLocal;


@Path("/member")
public class MemberRessource {
	@EJB
	MembreServiceLocal memservice;
    
	private User u ;
	
	
	//@Secured
	@POST
	@Consumes("application/json")
	public Response RegisterMember(User u){
	memservice.Registration(u);
		return Response.status(Status.CREATED).build();
	}
	
	
	
	
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
}

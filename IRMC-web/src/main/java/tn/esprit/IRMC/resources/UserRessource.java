package tn.esprit.IRMC.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.IRMC.persistence.User;
import tn.esprit.IRMC.services.MembreServiceLocal;
import tn.esprit.IRMC.services.UserServiceLocal;



@Path("/user")
public class UserRessource {
	@EJB
	UserServiceLocal usrservice;
    
	private User u ;
	
	
	List<User> listUSer;
	
	////********************** get all users*********///////////
	//@Token
	@GET
	@Produces("application/json")
	public Response getUsers(){
		return Response.ok(usrservice.getAllUser()).build();
	}
	
	//////////***************** add user***********///////
	@POST
	@Consumes("application/json")
	public Response AddUsers(User u){
	usrservice.addUser(u);
		return Response.status(Status.CREATED).build();
	}
	
	

	/////////////******************* delete user *****************////////////
	//@Token
	@DELETE
	@Path(value = "{id}")
	public Response deleteUser(@PathParam(value = "id") Integer i) {
		if (i != null) {
			u = usrservice.findUserById(i);
			if (u!= null) {
				usrservice.deleteUser(u.getIdUser());
				return Response.status(Status.OK).entity("user deleted").build();
			}
			return Response.status(Status.OK).entity("user not found").build();
		}
		return Response.status(Status.OK).entity(usrservice.getAllUser()).build();
	}
	//////////******************* update user*****************////////////
	//@Token
	@PUT
	@Consumes("application/json")
	@Path("updateU/")
	public Response updateUser(User u) {
		
		if (u != null) {
			usrservice.updateUser(u);
			return Response.status(Status.OK).entity("user updated").build();
		}
		return Response.status(Status.NOT_FOUND).entity(usrservice.getAllUser()).build();

	}
	
	
	////////********************* find by id ********************////////
	//@Token
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response getRendezVousById(@PathParam("id") Integer id) {
		if (id != null) {
			u = usrservice.findUserById(id);
			if (u != null) {
				return Response.status(Status.OK).entity(u).build();
			}
			return Response.status(Status.NOT_FOUND).entity("not found").build();
		}
		return Response.status(Status.OK).entity(usrservice.getAllUser()).build();

	}
	
	
	//*********************************** get by name **************//
	//@Token
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByName(@QueryParam(value = "first_name") String nom) {
		if (nom != null) {
			
				return Response.status(Status.OK).entity(usrservice.findUserByName(nom)).build();
			}
			return Response.status(Status.NO_CONTENT).entity("no user for this name").build();
		

	}
	
	
	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}
	
	
	// *************** Authentification*******************\\
	//@Token
	@GET
	@Produces("application/json")
	@Path("{username}/{password}")
	public Response Authenticate(@PathParam("username") String username, @PathParam("password") String password) {
		User u = usrservice.authentifier(username, password);
		if (u != null) {
			System.out.println(u);
			return Response.status(Status.ACCEPTED).entity(u).build();
		}

		else {
			System.out.println("no user !!!!!!!!");
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	// *************** FindUserByLogin*******************\\
	//@Token
	@GET
	//@Path(value = "{mail}")
	@Produces("application/json")
	public Response findUser(@QueryParam(value="mail")String mail) {
		if(mail != null){
		return Response.status(Status.OK).entity(usrservice.findMail(mail)).build();
		}
		return Response.status(Status.NOT_FOUND).entity(usrservice.getAllUser()).build();
	}
	

	// *************** Desactive Compte*******************\\
	//@Token
	@PUT
	@Consumes("application/json")
	@Path("desactive/{id}")
	public Response desactivateAccount(@PathParam("id") int id) {
		User u = new User();
		if(id!= 0){
		u = usrservice.findUserById(id);
		}
		if (usrservice.DesactiverCompte(u)) {
			System.out.println("Account desactivated !");
			return Response.status(Status.ACCEPTED)
					.entity("Account has been desactivated :( hope to see you soon again !").build();
		} else {
			System.out.println("Account desactivation failed");
			return Response.status(Status.OK).entity("Account desactivation failed").build();
		}
	}

	// *************** Changer pwd*******************\\
	//@Token
	/*@PUT
	@Consumes("application/json")
	@Path("{oldpwd}/{newpwd}/{id}")
	public Response changePassword(@PathParam("oldpwd") String oldPassword, @PathParam("newpwd") String newPassword,
			@PathParam("id") int id) {
		User u = new User();
		if(id !=0){
		u = usrservice.findUserById(id);}
		if (newPassword!=null && oldPassword!=null) {
			if (usrservice.changePassword(oldPassword, newPassword, u)) {
				return Response.status(Status.OK).entity("Password changed !").build();
			}
			return Response.status(Status.OK).entity("please check your old password !").build();

		}

		else {
			return Response.status(Status.OK).entity("Password must be not null!")
					.build();
		}
	}
*/
	// *************** Bloque Compte*******************\\
	//@Token
	@PUT
	@Consumes("application/json")
	@Path("/block/{id}")
	public Response Bloque(@PathParam("id") int id) {
		User u = new User();
		if(id!=0)
		{
			if(u!=null){
		u = usrservice.findUserById(id);
		usrservice.banAccount(u);
		return Response.status(Status.OK).entity("account bloqued").build();
			}
		}
		return	Response.status(Status.NOT_FOUND).entity("user not found ").build();

	}



//*************** Confirmer Compte*******************\\
	//@Token
	@PUT
	@Consumes("application/json")
	@Path("{token}/{id}")
	public Response confirmAccount(@PathParam("token") String token, @PathParam("id") int id) {
		User u = new User();
		u = usrservice.findUserById(id);
		if (usrservice.confirmAccount(token, u)) {
			return Response.status(Status.ACCEPTED).build();
		} else {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}

	}


	@EJB
	MembreServiceLocal memservice;
    
	
	
	@POST
	@Consumes("application/json")
	public Response RegisterMember(User u){
	memservice.Registration(u);
		return Response.status(Status.CREATED).build();
	}
	
	
}
package tn.esprit.IRMC.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
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

import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.services.OffreServiceImplLocal;

@Path("offre")
@RequestScoped
public class OffreResource {

	@EJB
	OffreServiceImplLocal offresmetier;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response getAllOffre(){
		
		return Response.ok(offresmetier.getAllCheckedOffre()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response AddOffre(Offre ofre){
		
		offresmetier.addOffre(ofre);
		return Response.accepted().build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response findOffreByid(@QueryParam(value="id") Integer id){
		
		if(id == 0 || id == null){
			return Response.ok(offresmetier.getAllOffre()).build();
		}else {
			return Response.ok(offresmetier.findOffreById(id)).build();
		}
		
		
		
	}
	
	
	@DELETE
	public Response DeleteOffre(@QueryParam(value="id") Integer id){
		

			Offre o = offresmetier.findOffreById(id);
	
				offresmetier.removeOffre(o);
				return Response.accepted().build();

	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOffre(Offre o){

			// o = offresmetier.findOffreById(o.getIdoffre());
				offresmetier.ubdateOffre(o);
				return Response.accepted().build();
			
		
	}
}

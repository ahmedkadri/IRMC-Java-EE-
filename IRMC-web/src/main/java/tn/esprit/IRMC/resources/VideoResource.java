package tn.esprit.IRMC.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.IRMC.persistence.Video;
import tn.esprit.IRMC.services.VideoServiceImplLocal;



@RequestScoped
@Path("videos")
public class VideoResource {

	private static List<Video> vids = new ArrayList<>();
	
	@EJB
	VideoServiceImplLocal vs;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response displayAllvideosByDate() {
		
		vids = vs.listAllVideo();
		if (!vids.isEmpty())
			return Response.status(Status.FOUND).entity(vids).build();
		return Response.status(Status.NO_CONTENT).entity(null).build();
	}
	
}

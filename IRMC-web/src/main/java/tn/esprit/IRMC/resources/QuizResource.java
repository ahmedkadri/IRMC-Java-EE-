package tn.esprit.IRMC.resources;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import tn.esprit.IRMC.persistence.Quiz;
import tn.esprit.IRMC.services.QuizServiceImplLocal;

@Path("quiz")
@RequestScoped
public class QuizResource {

	@EJB
	QuizServiceImplLocal quizmetier;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Quiz> getAllQuiz(){
		List<Quiz> q = quizmetier.getAllQuiz();
		return q;
	}
}

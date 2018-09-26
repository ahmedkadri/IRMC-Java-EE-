package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.persistence.Quiz;

@Remote
public interface QuizServiceImplRemote {

	public void addquiz(Quiz quiz);
	public Quiz findQuizByID(int id);
	public List<Quiz> getAllQuiz();
	public void removeQuiz(Quiz quiz);
	public void updateQuiz(Quiz quiz);
	
	public Quiz findQuizByName(String name);
}

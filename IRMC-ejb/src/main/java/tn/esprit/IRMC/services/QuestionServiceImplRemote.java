package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Question;

@Remote
public interface QuestionServiceImplRemote {

	public void addQuestion(Question q);
	public Question findQuestionById(int id);
	public List<Question> getAllQuestion();
	public void removeQuestion(Question q);
	public void updateQuestion(Question q);
	
	public Question findQuestionByQuestion(String question);
}

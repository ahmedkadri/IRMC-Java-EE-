package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Question;

@Local
public interface QuestionServiceImplLocal {

	public void addQuestion(Question q);
	public Question findQuestionById(int id);
	public List<Question> getAllQuestion();
	public void removeQuestion(Question q);
	public void updateQuestion(Question q);
	
	public Question findQuestionByQuestion(String question);
}

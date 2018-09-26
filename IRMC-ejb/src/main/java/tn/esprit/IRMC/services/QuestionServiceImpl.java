package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Question;
import tn.esprit.IRMC.persistence.Quiz;

/**
 * Session Bean implementation class QuestionServiceImpl
 */
@Stateless
public class QuestionServiceImpl implements QuestionServiceImplRemote, QuestionServiceImplLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	QuestionServiceImplLocal qsl;
	
    public QuestionServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addQuestion(Question q) {
		// TODO Auto-generated method stub
		em.persist(q);
	}

	@Override
	public Question findQuestionById(int id) {
		// TODO Auto-generated method stub
		return em.find(Question.class, id);
	}

	@Override
	public List<Question> getAllQuestion() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("allQuestion").getResultList() ;
	}

	@Override
	public void removeQuestion(Question q) {
		// TODO Auto-generated method stub
		em.remove(qsl.findQuestionById(q.getQuestion_id()));
	}

	@Override
	public void updateQuestion(Question q) {
		// TODO Auto-generated method stub
		em.merge(q);
	}

	@Override
	public Question findQuestionByQuestion(String question) {
		// TODO Auto-generated method stub
		
		TypedQuery<Question> query = em.createQuery
				("select q from Question q where q.question_actual like :n",Question.class);
		
		query.setParameter("n", question);
		
		return query.getSingleResult();
	}

}

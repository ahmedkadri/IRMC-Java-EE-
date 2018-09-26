package tn.esprit.IRMC.services;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.persistence.Quiz;

/**
 * Session Bean implementation class QuizServiceImpl
 */
@Stateless
public class QuizServiceImpl implements QuizServiceImplRemote, QuizServiceImplLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	
	@EJB
	QuizServiceImplLocal qsl;
	
    public QuizServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addquiz(Quiz quiz) {
		// TODO Auto-generated method stub
		em.persist(quiz);
	}

	@Override
	public Quiz findQuizByID(int id) {
		// TODO Auto-generated method stub
		return em.find(Quiz.class, id) ;
	}

	@Override
	public List<Quiz> getAllQuiz() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("allquiz").getResultList() ;
	}

	@Override
	public void removeQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		em.remove(qsl.findQuizByID(quiz.getQuiz_id()));
	}

	@Override
	public void updateQuiz(Quiz quiz) {
		// TODO Auto-generated method stub
		em.merge(quiz);
	}

	@Override
	public Quiz findQuizByName(String name) {
		// TODO Auto-generated method stub
		TypedQuery<Quiz> query = em.createQuery
				("select q from Quiz q where q.quiz_nom like :n",Quiz.class);
		query.setParameter("n", name);
		
		return query.getSingleResult();
	}

	

}

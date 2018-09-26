package tn.esprit.IRMC.services;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.IRMC.persistence.Condidature;
import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.persistence.Quiz;
import tn.esprit.IRMC.persistence.User;

/**
 * Session Bean implementation class OffreServiceImpl
 */
@Stateless
public class OffreServiceImpl implements OffreServiceImplRemote, OffreServiceImplLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	EntityManager em;
	
	@EJB
	OffreServiceImplLocal sol;
	
	@EJB
	UserServiceLocal usl;
	
    public OffreServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addOffre(Offre offre) {
		// TODO Auto-generated method stub
		em.persist(offre);
	}

	@Override
	public Offre findOffreById(int id) {
		// TODO Auto-generated method stub
		return em.find(Offre.class, id) ;
	}

	@Override
	public List<Offre> getAllOffre() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("allOffre").getResultList();
	}

	@Override
	public void removeOffre(Offre offre) {
		// TODO Auto-generated method stub

		em.remove(sol.findOffreById(offre.getIdoffre()));
	}

	@Override
	public void ubdateOffre(Offre offre) {
		// TODO Auto-generated method stub
		em.merge(offre);
	}

		
	@Override
	public List<Offre> getOffreByQuiz(Quiz quizz) {
		// TODO Auto-generated method stub
			
			String sql = "SELECT o FROM Offre o WHERE o.quiz2=:quizz";

	        Query query = em.createQuery(sql);
	        query.setParameter("quizz", quizz);

	        List<Offre> offres = query.getResultList();

	        return offres;
			
	}

	@Override
	public List<Offre> getAllCheckedOffre() {
		// TODO Auto-generated method stub
		Date today = new Date();
		
		String sql = "SELECT o FROM Offre o WHERE o.experationdate > :today";
		
		Query query = em.createQuery(sql);
		query.setParameter("today",today);
		
		List<Offre> offres = query.getResultList();
		return offres;
	}

	@Override
	public List<Condidature> getconbyoff(int id) {
		// TODO Auto-generated method stub
		
		Query q = em.createQuery("select c.listecondat from Offre c where c.idoffre =:id ");
		q.setParameter("id", id);
		
		return q.getResultList();
	}

	
		
		
	/*	Query query = em.createQuery("select o from Offre o where o.quiz like :n",Offre.class);
		query.setParameter("n", q);
		return query.getResultList();*/
	
	
	

}

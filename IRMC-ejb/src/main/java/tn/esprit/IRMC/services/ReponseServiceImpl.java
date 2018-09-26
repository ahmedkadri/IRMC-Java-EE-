package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.IRMC.persistence.Reponse;

/**
 * Session Bean implementation class ReponseServiceImpl
 */
@Stateless
public class ReponseServiceImpl implements ReponseServiceImplRemote, ReponseServiceImplLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
	
	@EJB
	ReponseServiceImplLocal rsl;
	
    public ReponseServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addReponse(Reponse r) {
		// TODO Auto-generated method stub
		em.persist(r);
	}

	@Override
	public Reponse findReponseById(int id) {
		// TODO Auto-generated method stub
		return em.find(Reponse.class, id);
	}

	@Override
	public List<Reponse> getAllReponse() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("allReponse").getResultList();
	}

	@Override
	public void removeReponse(Reponse r) {
		// TODO Auto-generated method stub
		em.remove(rsl.findReponseById(r.getReponse_id()));
	}

	@Override
	public void updateReponse(Reponse r) {
		// TODO Auto-generated method stub
		em.merge(r);
	}

}

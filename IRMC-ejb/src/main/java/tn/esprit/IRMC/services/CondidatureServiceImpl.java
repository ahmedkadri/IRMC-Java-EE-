package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.IRMC.persistence.Condidature;

/**
 * Session Bean implementation class CondidatureServiceImpl
 */
@Stateless
@LocalBean
public class CondidatureServiceImpl implements CondidatureServiceImplRemote, CondidatureServiceImplLocal {

    /**
     * Default constructor. 
     */
	
	@PersistenceContext
	EntityManager em;
	
    public CondidatureServiceImpl() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addCondidat(Condidature condidat) {
		// TODO Auto-generated method stub
		em.persist(condidat);
	}

	@Override
	public void updateCondidat(Condidature condidat) {
		// TODO Auto-generated method stub
		em.merge(condidat);
	}

	@Override
	public List<Condidature> getallCondidature() {
		// TODO Auto-generated method stub
		return em.createNamedQuery("allCondidat").getResultList();
	}

	@Override
	public Condidature findCondidatureByNum(int num) {
		// TODO Auto-generated method stub
		String sql = "SELECT o FROM Condidature o WHERE o.numeroc=:num";
		
		Query query = em.createQuery(sql);
        query.setParameter("num", num);
        return (Condidature) query.getSingleResult();
	}

}

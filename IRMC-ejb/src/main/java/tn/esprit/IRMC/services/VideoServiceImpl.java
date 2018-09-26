package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.IRMC.persistence.Video;

/**
 * Session Bean implementation class VideoServiceImpl
 */
@Stateless

public class VideoServiceImpl implements VideoServiceImplLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	EntityManager em;
    public VideoServiceImpl() {
        // TODO Auto-generated constructor stub
    }
	@Override
	public void uploadVideo(Video video) {
		// TODO Auto-generated method stub
		em.persist(video);
	}
	@Override
	public boolean checkTitle(Video video) {
		// TODO Auto-generated method stub
		List<Video> vids = 
				em
				.createQuery("select v from Video v where v.title=:x", Video.class)
				.setParameter("x", video.getTitle())
				.getResultList();
			return !vids.isEmpty();
	}
	@Override
	public List<Video> listAllVideo() {
		// TODO Auto-generated method stub
		List<Video> vids = 
				em
				.createQuery("select v from Video v ", Video.class)
				.getResultList();
			return vids;
	}
	@Override
	public List<Video> rechercherVideo(String recherche) {
		// TODO Auto-generated method stub
		return em.createQuery(" select v from Video v where v.title LIKE :x OR v.description LIKE :x OR v.uploadDate LIKE :x "
				,Video.class)
	    .setParameter("x", "%"+recherche+"%")
	    .getResultList();
	}
	@Override
	public int vuesOneVideo(Video video) {
		// TODO Auto-generated method stub
		return em.createQuery(" select SUM(v.vues) from Video v where v.getIdVideo:=x",Video.class)
				.setParameter("x", video.getIdVideo())
				.getFirstResult();
	}
	@Override
	public void deletevideo(Video video) {
		// TODO Auto-generated method stub
		em.remove(video);
	}

}

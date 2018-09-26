package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Condidature;
import tn.esprit.IRMC.persistence.Offre;
import tn.esprit.IRMC.persistence.Quiz;



@Local
public interface OffreServiceImplLocal {
	
	public void addOffre(Offre offre);
	public Offre findOffreById(int id);
	public List<Offre> getAllOffre();
	public void removeOffre(Offre offre);
	public void ubdateOffre(Offre offre);
	
	public List<Offre> getOffreByQuiz(Quiz quizz);
	public List<Offre> getAllCheckedOffre();
	
	public List<Condidature> getconbyoff(int id);
}

package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Reponse;

@Local
public interface ReponseServiceImplLocal {

	public void addReponse(Reponse r);
	public Reponse findReponseById(int id);
	public List<Reponse> getAllReponse();
	public void removeReponse(Reponse r);
	public void updateReponse(Reponse r);
}

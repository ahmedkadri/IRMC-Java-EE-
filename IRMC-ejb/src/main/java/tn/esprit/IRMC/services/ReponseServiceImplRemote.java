package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Reponse;

@Remote
public interface ReponseServiceImplRemote {
	
	public void addReponse(Reponse r);
	public Reponse findReponseById(int id);
	public List<Reponse> getAllReponse();
	public void removeReponse(Reponse r);
	public void updateReponse(Reponse r);
	

}

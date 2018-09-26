package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.IRMC.persistence.Condidature;

@Remote
public interface CondidatureServiceImplRemote {

	public void addCondidat(Condidature condidat);
	public void updateCondidat(Condidature condidat);
	public List<Condidature> getallCondidature();
}

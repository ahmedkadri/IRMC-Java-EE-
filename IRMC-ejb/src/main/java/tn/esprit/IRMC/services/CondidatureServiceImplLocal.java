package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Condidature;

@Local
public interface CondidatureServiceImplLocal {

	public void addCondidat(Condidature condidat);
	public void updateCondidat(Condidature condidat);
	public List<Condidature> getallCondidature();
	
	public Condidature findCondidatureByNum(int num);
}

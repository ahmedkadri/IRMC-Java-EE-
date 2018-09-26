package tn.esprit.IRMC.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.IRMC.persistence.Video;



@Local
public interface VideoServiceImplLocal {

	public void uploadVideo(Video video);
	public boolean checkTitle(Video video);
	public List<Video> listAllVideo();
	public List<Video> rechercherVideo(String recherche);
	public int vuesOneVideo(Video video);
	public void deletevideo(Video video);
}

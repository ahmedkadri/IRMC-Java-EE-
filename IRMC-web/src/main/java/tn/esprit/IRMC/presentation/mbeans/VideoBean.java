package tn.esprit.IRMC.presentation.mbeans;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import tn.esprit.IRMC.persistence.Video;
import tn.esprit.IRMC.services.VideoServiceImplLocal;

@ManagedBean
@ViewScoped
public class VideoBean {

	@EJB
	VideoServiceImplLocal vs;
	
	private List<Video> allVideos = new ArrayList<Video>();
	
	
	private Video video = new Video();
	private Part file;
	private String destination = "http://localhost:8787/";
	
	private String recherche="";
	public  Video chosenVideo = new Video();
	
	private String s;
	
	
	
	
	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	public List<Video> doListerRecherche(){
		return allVideos = vs.rechercherVideo(recherche);
	}
	
	public String plyvideo(Video vvv){
		
		this.setS(vvv.getURI());
		System.out.println(this.getS());
		//s = vvv.getURI();
		//chosenVideo.setURI(vvv.getURI());
		//this.setVideo(vvv);
		return "PlayVideo?faces-redirect=true";
	}
	public String deletevideo(Video v){
		vs.deletevideo(v);
		return "AllVideo?faces-redirect=true";
	}
	
	
	
	public String doUploadVideo() throws IOException {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
		video.setURI(destination + video.getTitle() + ".mp4");
		if (!vs.checkTitle(video)) {
			vs.uploadVideo(video);
			this.uploadFile(video.getTitle()+".mp4");
			return "AllVideo?faces-redirect=true";
		}
		else
		{
			System.out.println("mad5alch lil video");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Erreur! Upload de video n'est pas possible ! (Le Titre est déja utilisé)", "Upload de video pas possible ! Le titre de la video doit être unique"));
			video = new Video();
		}
		return "";
	}
	
	public void uploadFile(String nom) throws IOException{
		InputStream input = new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		input =  file.getInputStream();
		Files.copy(input, new File("c:/xampp/htdocs/", nom).toPath());
	}
	
	
	/*public String doPlayVideo() {
		chosenVideo.setVues(chosenVideo.getVues() + 1);
		vs.update(chosenVideo);
		return "/video/playvideo?faces-redirect=true";
	}*/
	

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public List<Video> getAllVideos() {
		return allVideos;
	}

	public void setAllVideos(List<Video> allVideos) {
		this.allVideos = allVideos;
	}

	public String getRecherche() {
		return recherche;
	}

	public void setRecherche(String recherche) {
		this.recherche = recherche;
	}

	public Video getChosenVideo() {
		return chosenVideo;
	}

	public void setChosenVideo(Video chosenVideo) {
		this.chosenVideo = chosenVideo;
	}






	
	
	
	
	
	
}

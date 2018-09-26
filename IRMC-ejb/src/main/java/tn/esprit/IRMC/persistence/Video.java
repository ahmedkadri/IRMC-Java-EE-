package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Video
 *
 */
@Entity
@Table(name = "mon_video")
public class Video implements Serializable {

	
	private int idVideo;
	private String URI;
	private String title;
	private String description;
	private Date uploadDate;
	
	private int vues;
	
	
	private static final long serialVersionUID = 1L;

	public Video() {
		super();
		this.vues = 0;
		this.uploadDate = new Date();
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdVideo() {
		return idVideo;
	}

	public void setIdVideo(int idVideo) {
		this.idVideo = idVideo;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getVues() {
		return vues;
	}

	public void setVues(int vues) {
		this.vues = vues;
	}
	
	
   
}

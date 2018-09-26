package tn.esprit.IRMC.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Reservation
 *
 */
@Entity

public class Reservation implements Serializable {

	

	private int ReservationId;
	private User Owner;
	private Date ReservationDate;
	private Date ReservationTime;
	private Date QuitTime;
	private Date ArrivalTime;
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public int getReservationId() {
		return ReservationId;
	}

	public void setReservationId(int reservationId) {
		ReservationId = reservationId;
	}

	@ManyToOne
	public User getOwner() {
		return Owner;
	}

	public void setOwner(User owner) {
		Owner = owner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;

	public Reservation() {
		super();
	}

	public Date getReservationDate() {
		return ReservationDate;
	}

	public void setReservationDate(Date reservationDate) {
		ReservationDate = reservationDate;
	}

	public Date getReservationTime() {
		return ReservationTime;
	}

	public void setReservationTime(Date reservationTime) {
		ReservationTime = reservationTime;
	}

	public Date getQuitTime() {
		return QuitTime;
	}

	public void setQuitTime(Date quitTime) {
		QuitTime = quitTime;
	}

	public Date getArrivalTime() {
		return ArrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		ArrivalTime = arrivalTime;
	}
	
	
	
	
	
	
	
   
}

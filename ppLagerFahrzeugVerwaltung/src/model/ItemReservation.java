package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the item_reservation database table.
 * @author Anja Skowasch, Julian Unsleber, Marcus Zitzelsberger, Markus Exner
 */
@Entity
@Table(name="item_reservation")
@NamedQuery(name="ItemReservation.findAll", query="SELECT i FROM ItemReservation i")
public class ItemReservation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="reservation_id")
	private int reservationId;

	@Temporal(TemporalType.DATE)
	private Date enddate;

	@Column(name="item_id")
	private int itemId;

	private int kilometer;

	private byte open;

	private byte overrun;

	@Temporal(TemporalType.DATE)
	private Date startdate;

	@Column(name="user_id")
	private int userId;

	public ItemReservation() {}

	public int getReservationId() {
		return this.reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getKilometer() {
		return this.kilometer;
	}

	public void setKilometer(int kilometer) {
		this.kilometer = kilometer;
	}

	public byte getOpen() {
		return this.open;
	}

	public void setOpen(byte open) {
		this.open = open;
	}

	public byte getOverrun() {
		return this.overrun;
	}

	public void setOverrun(byte overrun) {
		this.overrun = overrun;
	}

	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
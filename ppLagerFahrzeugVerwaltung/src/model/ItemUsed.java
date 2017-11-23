package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the item_used database table.
 * 
 */
@Entity
@Table(name="item_used")
@NamedQuery(name="ItemUsed.findAll", query="SELECT i FROM ItemUsed i")
public class ItemUsed implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_id")
	private int itemId;

	@Temporal(TemporalType.DATE)
	private Date date;

	@Column(name="type_id")
	private int typeId;

	@Column(name="user_id")
	private int userId;

	public ItemUsed() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
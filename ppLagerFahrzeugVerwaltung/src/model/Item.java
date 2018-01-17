package model;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the item database table.
 * 
 */
@Entity
@Table(name="item")
@NamedQuery(name="Item.findAll", query="SELECT i FROM Item i")
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="item_id")
	private int itemId;

	private String description;

	@Temporal(TemporalType.DATE)
	private Date entrydate;

	@Column(name="item_picture")
	private String itemPicture;

	private byte lent;

	private String itemName;

	private byte isOut;

	@Column(name="type_id")
	private int typeId;

	public Item() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}

	public String getItemPicture() {
		return this.itemPicture;
	}

	public void setItemPicture(String itemPicture) {
		this.itemPicture = itemPicture;
	}

	public byte getLent() {
		return this.lent;
	}

	public void setLent(byte lent) {
		this.lent = lent;
	}

	public String getName() {
		return this.itemName;
	}

	public void setName(String name) {
		this.itemName = name;
	}

	public byte getIsOut() {
		return this.isOut;
	}

	public void setIsOut(byte out) {
		this.isOut = out;
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

}
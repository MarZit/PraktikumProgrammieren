package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_type database table.
 * @author Anja Skowasch, Julian Unsleber, Marcus Zitzelsberger, Markus Exner
 */
@Entity
@Table(name="item_type")
@NamedQuery(name="ItemType.findAll", query="SELECT i FROM ItemType i")
public class ItemType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="type_id")
	private int typeId;

	@Column(name="type_kind")
	private int typeKind;

	@Column(name="type_name")
	private String typeName;

	public ItemType() {
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getTypeKind() {
		return this.typeKind;
	}

	public void setTypeKind(int typeKind) {
		this.typeKind = typeKind;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	@Override
	public String toString() {
		return this.getTypeName();
	}

}
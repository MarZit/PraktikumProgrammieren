package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the Item_type_role_relation database table.
 * 
 */
@Embeddable
public class ItemTypeRoleRelationPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="type_id")
	private int typeId;

	@Column(name="role_id")
	private int roleId;

	public ItemTypeRoleRelationPK() {
	}
	public int getTypeId() {
		return this.typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getRoleId() {
		return this.roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ItemTypeRoleRelationPK)) {
			return false;
		}
		ItemTypeRoleRelationPK castOther = (ItemTypeRoleRelationPK)other;
		return 
			(this.typeId == castOther.typeId)
			&& (this.roleId == castOther.roleId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.typeId;
		hash = hash * prime + this.roleId;
		
		return hash;
	}
}
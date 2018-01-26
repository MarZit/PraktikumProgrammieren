package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the item_type_role_relation database table.
 * @author Anja Skowasch, Julian Unsleber, Marcus Zitzelsberger, Markus Exner
 */
@Entity
@Table(name="item_type_role_relation")
@NamedQuery(name="ItemTypeRoleRelation.findAll", query="SELECT i FROM ItemTypeRoleRelation i")
public class ItemTypeRoleRelation implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemTypeRoleRelationPK id;

	public ItemTypeRoleRelation() {
	}

	public ItemTypeRoleRelationPK getId() {
		return this.id;
	}

	public void setId(ItemTypeRoleRelationPK id) {
		this.id = id;
	}

}
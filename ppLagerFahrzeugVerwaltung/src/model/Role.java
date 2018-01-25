package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the role database table.
 * @author Anja Skowasch, Julian Unsleber, Marcus Zitzelsberger, Markus Exner
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="role_id")
	private int roleId;

	@Column(name="role_name")
	private String roleName;

	public Role() {
	}

	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getRoleName();
	}

}
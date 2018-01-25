package userdata;

import javafx.beans.property.SimpleStringProperty;
import model.Role;
import model.User;

/**
 * 
 * @author Marcus Zitzelsberger
 *
 */

public class Userdata {

	private SimpleStringProperty username;
	private Role role;
	private User user;
	
	public Userdata(String username, Role role, User user) {
		this.username = new SimpleStringProperty(username);
		this.role = role;
		this.user = user;
	}

	public SimpleStringProperty usernameProperty() {
		return username;
	}

	public Role getRole() {
		return role;
	}
	
	public User getUser() {
		return this.user;
	}
	
	public boolean isAllowed() {
		if (role.getRoleId() == 1 || role.getRoleId() == 2) {
			return true;
		}
		
		else return false;
	}
}

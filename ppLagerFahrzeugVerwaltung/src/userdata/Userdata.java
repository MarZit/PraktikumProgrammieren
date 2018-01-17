package userdata;

import javafx.beans.property.SimpleStringProperty;
import model.Role;

public class Userdata {

	SimpleStringProperty username;
	Role role;
	
	public Userdata(String username, Role role) {
		this.username = new SimpleStringProperty(username);
		this.role = role;
	}

	public SimpleStringProperty usernameProperty() {
		return username;
	}

	public Role getRole() {
		return role;
	}
}

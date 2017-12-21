package userdata;

import javafx.beans.property.SimpleStringProperty;
import model.UserRole;

public class Userdata {

	SimpleStringProperty username;
	UserRole role;
	
	public Userdata(String username, UserRole role) {
		this.username = new SimpleStringProperty(username);
		this.role = role;
	}

	public SimpleStringProperty usernameProperty() {
		return username;
	}

	public UserRole getRole() {
		return role;
	}
}

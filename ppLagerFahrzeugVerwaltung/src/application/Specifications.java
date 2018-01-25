package application;

import java.util.Locale;
import java.util.ResourceBundle;

import userdata.Userdata;

/*
 * @author Marcus Zitzelsberger 
 */

public class Specifications {

	private static Specifications specs;
	
	private Userdata currentUser = new Userdata("test", null, null);
	
	private String serverIP = "85.214.197.82";
	private String database_dbName = "mydb";
	private String database_usrName = "myuser";
	private String database_usrPassword = "password";
	
	private String resourceBundleName = "resources/TextBundle";
	private Locale locale = Locale.ENGLISH;
	private ResourceBundle resources = ResourceBundle.getBundle(resourceBundleName, locale);
	
	private Specifications() {}
	
	public static Specifications getInstance() {
		if(specs == null) {
			specs = new Specifications();
		}
		return specs;
	}

	public String getDatabase_dbName() {
		return database_dbName;
	}
	
	public String getDatabase_usrName() {
		return database_usrName;
	}

	public String getDatabase_usrPassword() {
		return database_usrPassword;
	}

	public String getServerIP() {
		return serverIP;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public ResourceBundle getResources() {
		return resources;
	}

	public void setResources(ResourceBundle resources) {
		this.resources = resources;
	}

	public String getResourceBundleName() {
		return resourceBundleName;
	}

	public Userdata getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(Userdata currentUser) {
		this.currentUser = currentUser;
	}
	
}

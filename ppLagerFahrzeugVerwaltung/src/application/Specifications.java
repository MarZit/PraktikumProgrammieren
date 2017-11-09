package application;

public class Specifications {

	private Specifications specs;
	
	private String database_usrName = "root";
	private String database_usrPassword = "root";
	
	private Specifications() {}
	
	public Specifications getInstance() {
		if(specs == null) {
			specs = new Specifications();
		}
		return specs;
	}

	public String getDatabase_usrName() {
		return database_usrName;
	}

	public String getDatabase_usrPassword() {
		return database_usrPassword;
	}
}

package databaseLager;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Klasse dient zum Erstellen der Datenbank, sowie dem Einlesen von Standarddaten
 * @author Julian Unsleber, Anja Skowasch
 *
 */
public class DatabaseCreator {
    
    private Connection connection;
    
    public Connection getConnection(String dbname) throws Exception {
	if (connection == null) {
	    Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
	    DriverManager.registerDriver(driver);
	    connection = DriverManager.getConnection("jdbc:mysql://85.214.197.82:3306/"  + "?useSSL=true", "myuser", "password");	    
	}
	
	return connection;
	
    }
	
    
    public void createSchema () throws Exception {
	
	Statement stmt = getConnection("mydb").createStatement();
	for(String sql: getCreateDDL()){
	    System.out.println(sql);
	    stmt.execute(sql);
	}
	stmt.close();
	connection = null;
	
    }
    
    
    public void createTriggerAndEvents () throws Exception {
    	
    	Statement stmt = getConnection("mydb").createStatement();
    	for(String sql: getCreateDDLTriggerAndEvents()){
    	    System.out.println(sql);
    	    stmt.execute(sql);
    	}
    	stmt.close();
    	connection = null;
    	
        }
    
    public void createData() throws Exception {
	Statement stmt = getConnection("mydb").createStatement();
	for(String sql: getCreateDDLData()){
	    System.out.println(sql);
	    stmt.execute(sql);
	}
	stmt.close();
	connection = null;
	
    }
    
    
   


    private String[] getCreateDDL() throws IOException{
	InputStream in = getClass().getResourceAsStream("SQL-Befehl.sql");
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String line, sql = "";
	while((line = br.readLine()) != null) {
	    sql += line + "\n";
	}
	br.close();
	
	return sql.split("[;]");
	
    }
    
    private String[] getCreateDDLData() throws IOException{
	InputStream in = getClass().getResourceAsStream("SQL-Daten2.sql");
	BufferedReader br = new BufferedReader(new InputStreamReader(in));
	String line, sql = "";
	while((line = br.readLine()) != null) {
	    sql += line + "\n";
	}
	br.close();
	
	return sql.split("[;]");
    }
    
    private String[] getCreateDDLTriggerAndEvents() throws IOException{
    	InputStream in = getClass().getResourceAsStream("Trigger and Events.sql");
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	String line, sql = "";
    	while((line = br.readLine()) != null) {
    	    sql += line + "\n";
    	}
    	br.close();
    	
    	return sql.split("[;]");
        }
    
    /** Clean database **/
    private String[] getResetDatabase() throws IOException {
    	InputStream in = getClass().getResourceAsStream("SQL-Reset.sql");
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	String line, sql = "";
    	while((line = br.readLine()) != null) {
    	    sql += line + "\n";
    	}
    	br.close();
    	
    	return sql.split("[;]");
    }
    
    public void resetDatabase() throws Exception {
    	Statement stmt = getConnection("mydb").createStatement();
    	for(String sql: getResetDatabase()){
    	    System.out.println(sql);
    	    stmt.execute(sql);
    	}
    	stmt.close();
    	connection = null;
    	
    }
}
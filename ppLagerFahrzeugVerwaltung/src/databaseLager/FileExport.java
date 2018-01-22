package databaseLager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.stage.FileChooser;

public class FileExport {
	
	/**
	 * 
	 * @author Markus Exner
	 *
	 */

	private Connection connection;
	private String     usr_name	= "root";
	private String     password	= "m37P48z!";
	
	 private Connection getConnection(String dbname) {
		 try {
			if (connection == null || connection.isClosed()) {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + dbname + "?useSSL=false", usr_name, password);
			}
			} catch (Exception e)
		 		{
				System.err.println("Error while establishing connection to Database " + e);
		 		}
			return connection;
	 }
	 
	 public void startExport() throws SQLException, IOException {
		 Connection con = getConnection("database");
		 String fileExtension = ".csv";
		 FileChooser fileChooser = new FileChooser();
		 fileChooser.setTitle("Choose Location to save the export");
		 File selectedFile = null;
		 while (selectedFile == null) {
			 selectedFile = fileChooser.showSaveDialog(null);
		 }
		 FileWriter exportFile = new FileWriter(selectedFile + fileExtension);;
		 String query = "select itemName, description, entrydate from item;";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
    	 exportFile.append("Name");
    	 exportFile.append(',');
    	 exportFile.append("Beschreibung");
    	 exportFile.append(',');
    	 exportFile.append("Eingetragen am");
    	 exportFile.append('\n');
         while (rs.next()) {
             exportFile.append(rs.getString(1));
             exportFile.append(',');
             exportFile.append(rs.getString(2));
             exportFile.append(',');
             exportFile.append(rs.getString(3));
             exportFile.append('\n');
            }
         exportFile.flush();
         exportFile.close();
         con.close();
         System.out.println("CSV File is created successfully.");
	 }
	
}

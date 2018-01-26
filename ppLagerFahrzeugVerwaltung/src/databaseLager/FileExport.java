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
	private String     usr_name	= "myuser";
	private String     password	= "password";
	
	 private Connection getConnection(String dbname) {
		 try {
			if (connection == null || connection.isClosed()) {
			Driver driver = (Driver) Class.forName("com.mysql.jdbc.Driver").newInstance();
			DriverManager.registerDriver(driver);
			connection = DriverManager.getConnection("jdbc:mysql://85.214.197.82:3306/" + dbname + "?useSSL=false", usr_name, password);
			}
			} catch (Exception e)
		 		{
				System.err.println("Error while establishing connection to Database " + e);
		 		}
			return connection;
	 }
	 
	 private String getFileExtension(File file) {
		 String name = file.getName();
		 try {
			 return name.substring(name.lastIndexOf(".") + 1);
		 } catch (Exception e) {
			System.out.println("Fehler bei getFileExtension Funktion" + e);
		}
		 return "";
	 }
	 
	 private boolean isCSV(String extension) {
		 if (extension.equals("csv")) {
			 return true;
		 }
		 return false;
	 }
	 
	 public void startExport() throws SQLException, IOException {
		 Connection con = getConnection("mydb");
		 FileChooser fileChooser = new FileChooser();
		 String fileExtension = ".csv";
		 FileWriter exportFile;
		 fileChooser.setTitle("Choose Location to save the export");
		 File selectedFile = null;
		 selectedFile = fileChooser.showSaveDialog(null);
		 if (selectedFile == null) return;
		 String alreadyExtension = getFileExtension(selectedFile);
		 		 
		 if (isCSV(alreadyExtension)) {
			 exportFile = new FileWriter(selectedFile);
		 } else {
			 exportFile = new FileWriter(selectedFile + fileExtension);
		 	}
		 
		 String query = "select itemName, description, entrydate from item;";
		 Statement stmt = con.createStatement();
		 ResultSet rs = stmt.executeQuery(query);
    	 exportFile.append("Name");
    	 exportFile.append('\t');
    	 exportFile.append("Beschreibung");
    	 exportFile.append('\t');
    	 exportFile.append("Eingetragen am");
    	 exportFile.append('\n');
         while (rs.next()) {
             exportFile.append(rs.getString(1));
             exportFile.append('\t');
             exportFile.append(rs.getString(2));
             exportFile.append('\t');
             exportFile.append(rs.getString(3));
             exportFile.append('\n');
            }
         exportFile.flush();
         exportFile.close();
         con.close();
	 }
	
}

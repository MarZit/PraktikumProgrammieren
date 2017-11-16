package databaseLager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseCreator 
{
	 private Connection connection;
	 private String     usr_name	= "root";
	 private String     password	= "root";
	 
	 private Connection getConnection(String dbname)
	 {
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
	 
	 public void createSchema()
	 {
	 try {
		 Connection con =  getConnection("mysql");	
		 Statement stmt = con.createStatement();
		 con.setAutoCommit(false);
		 for (String sql : getCreateDDL())
		 {
			 stmt.execute(sql);
		 }
		 } catch (SQLException e) 
	 	 {
			System.out.println("An Error occured while creating the schema"+e);
			e.printStackTrace();
		 }
		 
	 };
	 
	 //Name unserer Datenbank?
	 private String[] getCreateDDL()
	 {
		 try 
		 {
		 InputStream inSt = getClass().getResourceAsStream("database");
		 BufferedReader br = new BufferedReader(new InputStreamReader(inSt));
		 String line, sql ="";
		 while((line = br.readLine())!=null)
		 	{
			 sql+= line + "\n";
			 }
		 return sql.split("[;]");
		} catch (IOException e) {
			System.out.println("An Error occured while reading the sql-file"+e);
			e.printStackTrace();
		}
		return null;
	 };
}

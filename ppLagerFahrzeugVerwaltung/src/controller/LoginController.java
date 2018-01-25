//Anja Skowasch
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import application.Specifications;
import databaseLager.Queries;
import guiLager.LoginWindow;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.User;
import userdata.Userdata;

/**
 * 
 * @author Anja Skowasch
 *
 */

public class LoginController
{ 
	String userName;
	String passWord;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	LoginWindow loginWindow;
	
	public LoginController(String username, String password, LoginWindow loginWindow)
	{
		this.userName = username;
		this.passWord = password;
		this.loginWindow = loginWindow;
		authenticateQueryies();
	}

	private void authenticateQueryies()
	{
		try
		{
		Queries queries = new Queries();
		User res = queries.getUserByNameAndPassword(userName,passWord);
		Queries q1 = new Queries();
		Userdata user = new Userdata(res.getUsername(),  q1.getRoleByRoleID(res.getRole()), res);
		Specifications.getInstance().setCurrentUser(user);
		this.loginWindow.close();
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			infoBox("Enter Correct User and Password", "Failed", null);
		}
	}

	 

	 
	public static void infoBox(String infoMessage, String titleBar, String headerMessage)
	{
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle(titleBar);
	alert.setHeaderText(headerMessage);
	alert.setContentText(infoMessage);
	alert.showAndWait();
	}
	 
}

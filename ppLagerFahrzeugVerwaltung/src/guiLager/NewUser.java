package guiLager;


import application.Specifications;

import controller.StoreController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Role;
import model.User;

/**
 * 
 * @author Markus Exner
 *
 */

public class NewUser extends Stage {
	
	private StoreController storeController;
	private GridPane newUserGridPane;
	private User user = null;
	
	public NewUser(StoreController storeController) {
		this.storeController = storeController;
		this.user = new User();
		this.newUserGridPane = new GridPane();
		setUpGridPane();
		Scene newUserScene = new Scene(newUserGridPane, 220, 200);
		setScene(newUserScene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	public void setUpGridPane() {
		TextField userNameTF = new TextField();
		userNameTF.setPromptText(Specifications.getInstance().getResources().getString("userName"));
		
		TextField userFirstNameTF = new TextField();
		userFirstNameTF.setPromptText(Specifications.getInstance().getResources().getString("firstNameUser"));
		
		TextField userLastName = new TextField();
		userLastName.setPromptText(Specifications.getInstance().getResources().getString("lastNameUser"));
		
		TextField emailTF = new TextField();
		emailTF.setPromptText(Specifications.getInstance().getResources().getString("email"));
		
		TextField password = new TextField();
		password.setPromptText(Specifications.getInstance().getResources().getString("password"));
		
		ComboBox<Role> roleBox = new ComboBox<>();
		roleBox.setPromptText(Specifications.getInstance().getResources().getString("userRole"));
		roleBox.getItems().addAll(storeController.getRolesFromDatabase());
		Button submitButton = new Button(Specifications.getInstance().getResources().getString("ok"));
		this.user.setNewUser((byte)1);
		submitButton.setOnAction(e -> {
			this.user.setUsername(userNameTF.getText());
			this.user.setFirstName(userFirstNameTF.getText());
			this.user.setLastName(userLastName.getText());
			this.user.setEmail(emailTF.getText());
			this.user.setPassword(password.getText());
			this.user.setRole(1);
			this.user.setNewUser((byte)1);
			this.storeController.writeNewUser(user);
			this.close();
		});
		newUserGridPane.getChildren().addAll(userNameTF, userFirstNameTF, userLastName, emailTF, password, roleBox, submitButton);	
		GridPane.setConstraints(userNameTF, 0, 0);
		GridPane.setConstraints(userFirstNameTF, 0, 1);
		GridPane.setConstraints(userLastName, 0, 2);
		GridPane.setConstraints(emailTF, 0, 3);
		GridPane.setConstraints(password, 0, 4);
		GridPane.setConstraints(roleBox, 0, 5);
		GridPane.setConstraints(submitButton, 0, 6);
	}

}

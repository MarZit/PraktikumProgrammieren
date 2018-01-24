package guiLager;

import application.Specifications;
import controller.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginWindow extends Stage {
	
	public LoginWindow(Stage parentStage) {
		setTitle(Specifications.getInstance().getResources().getString("login"));
		BorderPane loginPane = new BorderPane();
		loginPane.setCenter(initCenter());
		Scene loginScene = new Scene(loginPane);
		setScene(loginScene);
		initOwner(parentStage);
		initModality(Modality.APPLICATION_MODAL);
		setOnCloseRequest( e -> {
			System.exit(0);
		});
	}

	private Node initCenter() {
		GridPane centerGridPane = new GridPane();
		Label usernameLabel = new Label(Specifications.getInstance().getResources().getString("username") + ":");
		TextField usernameTextField = new TextField();
		usernameTextField.setPromptText(Specifications.getInstance().getResources().getString("username"));
		centerGridPane.getChildren().addAll(usernameLabel, usernameTextField);
		GridPane.setConstraints(usernameLabel, 0, 0);
		GridPane.setConstraints(usernameTextField, 1, 0);
		Label passwordLabel = new Label(Specifications.getInstance().getResources().getString("password") + ":");
		PasswordField passwordTextField = new PasswordField();
		passwordTextField.setPromptText(Specifications.getInstance().getResources().getString("password"));
		Button loginButton = new Button(Specifications.getInstance().getResources().getString("login"));
		loginButton.setOnAction( e -> {
			String username = usernameTextField.getText().toString();
			String password = passwordTextField.getText().toString();
			LoginController lc = new LoginController(username,password, this);
		});
		
		centerGridPane.getChildren().addAll(passwordLabel, passwordTextField, loginButton);
		GridPane.setConstraints(passwordLabel, 0, 1);
		GridPane.setConstraints(passwordTextField, 1, 1);
		GridPane.setConstraints(loginButton, 1, 2);
		GridPane.setMargin(usernameLabel, new Insets(10.0));
		GridPane.setMargin(usernameTextField, new Insets(10.0));
		GridPane.setMargin(passwordLabel, new Insets(10.0));
		GridPane.setMargin(passwordTextField, new Insets(10.0));
		GridPane.setMargin(loginButton, new Insets(10.0));
		return centerGridPane;
	}
}
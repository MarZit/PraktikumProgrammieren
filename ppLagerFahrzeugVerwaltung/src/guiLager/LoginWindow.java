package guiLager;

import application.Specifications;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
		TextField passwordTextField = new TextField();
		passwordTextField.setPromptText(Specifications.getInstance().getResources().getString("password"));
		centerGridPane.getChildren().addAll(passwordLabel, passwordTextField);
		GridPane.setConstraints(passwordLabel, 0, 1);
		GridPane.setConstraints(passwordTextField, 1, 1);
		GridPane.setMargin(usernameLabel, new Insets(10.0));
		GridPane.setMargin(usernameTextField, new Insets(10.0));
		GridPane.setMargin(passwordLabel, new Insets(10.0));
		GridPane.setMargin(passwordTextField, new Insets(10.0));
		return centerGridPane;
	}
}

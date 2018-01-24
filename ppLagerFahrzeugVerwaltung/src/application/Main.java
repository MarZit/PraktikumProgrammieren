package application;
	
import databaseLager.DatabaseCreator;
import guiLager.ContainerPane;
import guiLager.LoginWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * @author Marcus Zitzelsberger 
 */

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle(Specifications.getInstance().getResources().getString("title"));
			ContainerPane containerPane = new ContainerPane();
			Scene scene = new Scene(containerPane, 1200, 900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			LoginWindow loginWindow = new LoginWindow(primaryStage);
			loginWindow.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println("Erstelle Datenbank...");
		DatabaseCreator db = new DatabaseCreator();
		db.createSchema();
		System.out.println("...fertig");
		launch(args);
	}
}

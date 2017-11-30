package guiLager;

import application.Language;
import application.Specifications;
import javafx.geometry.Pos;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

/*
 * @author Marcus Zitzelsberger 
 */

public class ContainerPane extends BorderPane {
	
	public ContainerPane() {
		initTop();
		initCenter();
		initLeft();
		initRight();
		initBottom();
	}

	private void initTop() {
		ToolBar topToolBar = new ToolBar();
		HBox topHBox = new HBox();
		MenuBar topMenuBar = new MenuBar();
		Menu userMenu = new Menu();
		userMenu.textProperty().bind(Specifications.getInstance().getCurrentUser().usernameProperty());
		Menu databaseMenu = new Menu();
		databaseMenu.setText(Specifications.getInstance().getResources().getString("database"));
		MenuItem addNewItemMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("addItem"));
		MenuItem removeItemMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("removeItem"));
		databaseMenu.getItems().addAll(addNewItemMenuItem, removeItemMenuItem);
		Menu statisticsMenu = new Menu();
		statisticsMenu.setText(Specifications.getInstance().getResources().getString("statistics"));
		topMenuBar.getMenus().addAll(userMenu, databaseMenu, statisticsMenu);
		HBox languageButtonsBox = new HBox(10);
		LanguageButton buttonEnglish = new LanguageButton(Language.EN);
		LanguageButton buttonGerman = new LanguageButton(Language.DE);
		languageButtonsBox.getChildren().addAll(buttonEnglish, buttonGerman);
		languageButtonsBox.setAlignment(Pos.CENTER_RIGHT);
		topHBox.getChildren().addAll(topMenuBar, languageButtonsBox);
		HBox.setHgrow(topMenuBar, Priority.ALWAYS);
		topToolBar.getItems().add(topHBox);
		setTop(topHBox);
	}

	private void initRight() {
		// TODO Auto-generated method stub
		
	}
	
	private void initBottom() {
		// TODO Auto-generated method stub
		
	}

	private void initLeft() {
		// TODO Auto-generated method stub
		
	}

	private void initCenter() {
		// TODO Auto-generated method stub
		
	}
}

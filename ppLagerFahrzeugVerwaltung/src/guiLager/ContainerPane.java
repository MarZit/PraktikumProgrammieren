package guiLager;

import javax.swing.GroupLayout.Alignment;

import application.Language;
import application.Specifications;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Item;

/*
 * @author Marcus Zitzelsberger 
 */

public class ContainerPane extends BorderPane {
	
	CenterGridPane centerGridPane = null;
	
	public ContainerPane() {
		initTop();
		if(centerGridPane == null) {
			initCenter();
		}
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
		VBox leftVBox = new VBox(10);
		ComboBox<Item> superCategoriesComboBox = new ComboBox<>();
		superCategoriesComboBox.setPrefWidth(100.0);
//		superCategoriesComboBox.getItems().addAll(arg0) add Query here
		ComboBox<Item> subCategoriesComboBox = new ComboBox<>();
		subCategoriesComboBox.setPrefWidth(100.0);
//		subCategoriesComboBox.getItems().addAll(arg0) add Query here
		CalendarPane leftCalendarPane = new CalendarPane();
		leftVBox.getChildren().addAll(superCategoriesComboBox, subCategoriesComboBox, leftCalendarPane);
		setLeft(leftVBox);
	}

	private void initCenter() {
		ScrollPane centerScrollPane = new ScrollPane();
		centerGridPane = new CenterGridPane();
		centerScrollPane.setContent(centerGridPane);
		setCenter(centerScrollPane);
	}
	
	public CenterGridPane getCenterGridPane() {
		if(centerGridPane == null) {
			initCenter();
		}
		return centerGridPane;
	}
}

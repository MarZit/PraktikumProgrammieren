package guiLager;

import java.util.ArrayList;

import application.Language;
import application.Specifications;
import application.TypeKindEnum;
import controller.StoreController;
import databaseLager.DatabaseCreator;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import model.Item;
import model.ItemType;
import userdata.Userdata;

/**
 * @author Marcus Zitzelsberger, edited by Markus Exner 
 */

public class ContainerPane extends BorderPane {
	
	private CenterGridPane centerGridPane = null;
	private StoreController storeController = null;
	private ComboBox<ItemType> subCategoriesComboBox;
	private ComboBox<TypeKindEnum> superCategoriesComboBox;
	private Userdata currentUser;

	
	
	public ContainerPane() {
		storeController = new StoreController();
		this.currentUser = Specifications.getInstance().getCurrentUser();
		initTop();
		initLeft();
		if(centerGridPane == null) {
			initCenter();
		}
		initRight();
		initBottom();
		
	}

	private void initTop() {
		ToolBar topToolBar = new ToolBar();
		HBox topHBox = new HBox();
		MenuBar topMenuBar = new MenuBar();
		Menu userMenu = new Menu();
		// Add new user
		MenuItem addUser = new MenuItem(Specifications.getInstance().getResources().getString("addUser"));
		userMenu.textProperty().bind(Specifications.getInstance().getCurrentUser().usernameProperty());
		Menu databaseMenu = new Menu();
		databaseMenu.setText(Specifications.getInstance().getResources().getString("database"));
		MenuItem addNewItemMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("addItem"));
		addNewItemMenuItem.setOnAction(e->{
			NewItemWindow newItemWindow = new NewItemWindow(storeController, this);
			newItemWindow.showAndWait();
		});
		//Lagerverwaltung und Admin können neue Items hinzufügen
		addNewItemMenuItem.setVisible(this.currentUser.isAllowed());
		MenuItem addNewItemTypeMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("addItemType"));
		addNewItemTypeMenuItem.setOnAction(e->{
			NewItemTypeWindow newItemTypeWindow = new NewItemTypeWindow(storeController, this);
			newItemTypeWindow.showAndWait();
		});
		addNewItemTypeMenuItem.setVisible(this.currentUser.isAllowed());
		MenuItem exportDatabase = new MenuItem(Specifications.getInstance().getResources().getString("export"));
		exportDatabase.setOnAction(e -> {
			storeController.exportDatabase();
		});
		/*Datenbank mit Testdaten füllen*/
		MenuItem fillDatabase = new MenuItem(Specifications.getInstance().getResources().getString("fillDatabase"));
		fillDatabase.setOnAction(e -> {
			DatabaseCreator db = new DatabaseCreator();
			try {
				db.createData();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		addUser.setVisible(this.currentUser.isAllowed());
		addUser.setOnAction(e -> {
			NewUser newUser = new NewUser(this.storeController);
			newUser.showAndWait();
		});
		databaseMenu.getItems().addAll(addNewItemMenuItem, addNewItemTypeMenuItem, exportDatabase, fillDatabase);
//		Menu statisticsMenu = new Menu();
//		statisticsMenu.setText(Specifications.getInstance().getResources().getString("statistics"));
		topMenuBar.getMenus().addAll(userMenu, databaseMenu);
		userMenu.getItems().add(addUser);
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

	public void initLeft() {
		VBox leftVBox = new VBox(10);
		superCategoriesComboBox = new ComboBox<>();
		superCategoriesComboBox.setPrefWidth(100.0);
		superCategoriesComboBox.getItems().addAll(TypeKindEnum.getAll()); //add Query here
		superCategoriesComboBox.getSelectionModel().select(0);
		superCategoriesComboBox.setOnAction(e->{
			subCategoriesComboBox.getItems().clear();
			subCategoriesComboBox.getItems().addAll(storeController.getItemKindTypeFromDatabase(superCategoriesComboBox.getSelectionModel().getSelectedItem())); 
		});
		subCategoriesComboBox = new ComboBox<>();
		subCategoriesComboBox.setPrefWidth(100.0);
		subCategoriesComboBox.getItems().addAll(storeController.getItemKindTypeFromDatabase(superCategoriesComboBox.getSelectionModel().getSelectedItem())); 
		subCategoriesComboBox.setOnAction(e -> {
		initCenter();
		});
		CalendarPane leftCalendarPane = new CalendarPane(this, this.storeController);
		leftVBox.getChildren().addAll(superCategoriesComboBox, subCategoriesComboBox, leftCalendarPane);
		setLeft(leftVBox);
		
	}

	public void initCenter() {
		ScrollPane centerScrollPane = new ScrollPane();
		centerGridPane = new CenterGridPane(this);
		ItemType itmType = subCategoriesComboBox.getSelectionModel().getSelectedItem();
		if(itmType != null) {
			int itemTypeID = itmType.getTypeId();
			ArrayList<Item> itemList = storeController.getItemsFromDatabase(itemTypeID);
			for (Item item : itemList) {
				centerGridPane.addItemToList(item, this);
			}
		}
		centerScrollPane.setContent(centerGridPane);
		setCenter(centerScrollPane);
		
	}
	
	public CenterGridPane getCenterGridPane() {
		if(centerGridPane == null) {
			initCenter();
		}
		return centerGridPane;
	}
	
	public ComboBox<ItemType> getSubCategoriesComboBox() {
		if (this.subCategoriesComboBox == null) {
			initLeft();
		}
		return this.subCategoriesComboBox;
	}
}

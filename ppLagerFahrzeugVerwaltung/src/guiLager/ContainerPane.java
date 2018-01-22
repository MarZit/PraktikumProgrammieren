package guiLager;

import java.util.ArrayList;

import application.Language;
import application.Specifications;
import application.TypeKindEnum;
import controller.StoreController;
import databaseLager.Queries;
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

/*
 * @author Marcus Zitzelsberger 
 */

public class ContainerPane extends BorderPane {
	
	private CenterGridPane centerGridPane = null;
	private StoreController storeController = null;
	private ComboBox<ItemType> subCategoriesComboBox;
	private ComboBox<TypeKindEnum> superCategoriesComboBox;

	
	
	public ContainerPane() {
		storeController = new StoreController();
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
		userMenu.textProperty().bind(Specifications.getInstance().getCurrentUser().usernameProperty());
		Menu databaseMenu = new Menu();
		databaseMenu.setText(Specifications.getInstance().getResources().getString("database"));
		MenuItem addNewItemMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("addItem"));
		addNewItemMenuItem.setOnAction(e->{
			NewItemWindow newItemWindow = new NewItemWindow(storeController, this);
			newItemWindow.showAndWait();
		});
		MenuItem addNewItemTypeMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("addItemType"));
		addNewItemTypeMenuItem.setOnAction(e->{
			NewItemTypeWindow newItemTypeWindow = new NewItemTypeWindow(storeController, this);
			newItemTypeWindow.showAndWait();
		});
		MenuItem removeItemMenuItem = new MenuItem(Specifications.getInstance().getResources().getString("removeItem"));
		MenuItem exportDatabase = new MenuItem(Specifications.getInstance().getResources().getString("export"));
		exportDatabase.setOnAction(e -> {
			storeController.exportDatabase();
		});
		databaseMenu.getItems().addAll(addNewItemMenuItem, addNewItemTypeMenuItem, removeItemMenuItem, exportDatabase);
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
		CalendarPane leftCalendarPane = new CalendarPane();
		leftVBox.getChildren().addAll(superCategoriesComboBox, subCategoriesComboBox, leftCalendarPane);
		setLeft(leftVBox);
		
	}

	public void initCenter() {
		ScrollPane centerScrollPane = new ScrollPane();
		centerGridPane = new CenterGridPane();
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

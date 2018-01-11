package guiLager;

import java.util.Calendar;

import application.Specifications;
import controller.StoreController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Item;
import model.ItemType;

public class NewItemWindow extends Stage {
	GridPane newItemPane;
	Item item = null;
	StoreController storeController;
	
	public NewItemWindow(StoreController storeController) {
		this.storeController = storeController;
		item = new Item();
		newItemPane = new GridPane();
		setUpGridPane();
		Scene newItemScene = new Scene(newItemPane, 220, 200);
		setScene(newItemScene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	private void setUpGridPane() {
		TextField descriptionTF = new TextField();
		descriptionTF.setPromptText(Specifications.getInstance().getResources().getString("description"));
		Calendar cal = Calendar.getInstance();
		item.setEntrydate(cal.getTime());
		Button fcButton = new Button(Specifications.getInstance().getResources().getString("choosePicture"));
		fcButton.setOnAction(e->{
			FileChooser fc = new FileChooser();
			item.setItemPicture(fc.showOpenDialog(this).toString());
		});
		item.setLent((byte)0);
		TextField nameTF = new TextField();
		nameTF.setPromptText(Specifications.getInstance().getResources().getString("name"));
		item.setOut((byte)0);
		ComboBox<ItemType> typeBox = new ComboBox<>();
		typeBox.setPromptText(Specifications.getInstance().getResources().getString("itemType"));
		typeBox.getItems().addAll(storeController.getItemTypesFromDatabase());
		Button submitButton = new Button(Specifications.getInstance().getResources().getString("ok"));
		submitButton.setOnAction(e->{
			item.setDescription(descriptionTF.getText());
			item.setName(nameTF.getText());
			item.setTypeId(typeBox.getValue().getTypeId());
			storeController.writeItemToDatabase(item);
		});
		newItemPane.getChildren().addAll(nameTF, descriptionTF, fcButton, typeBox, submitButton);	
		GridPane.setConstraints(nameTF, 0, 0);
		GridPane.setConstraints(descriptionTF, 0, 1);
		GridPane.setConstraints(fcButton, 0, 2);
		GridPane.setConstraints(typeBox, 0, 3);
		GridPane.setConstraints(submitButton, 0, 4);
	}
}
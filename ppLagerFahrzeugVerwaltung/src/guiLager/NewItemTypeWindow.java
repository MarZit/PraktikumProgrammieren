package guiLager;

import application.Specifications;
import application.TypeKindEnum;
import controller.StoreController;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ItemType;

public class NewItemTypeWindow extends Stage {
	GridPane newItemTypePane;
	ItemType itemType = null;
	StoreController storeController;
	
	public NewItemTypeWindow(StoreController storeController) {
		this.storeController = storeController;
		itemType = new ItemType();
		newItemTypePane = new GridPane();
		setUpGridPane();
		Scene newItemScene = new Scene(newItemTypePane, 150, 150);
		setScene(newItemScene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	private void setUpGridPane() {
		TextField typeNameTF = new TextField();
		typeNameTF.setPromptText(Specifications.getInstance().getResources().getString("name"));
		ComboBox<TypeKindEnum> typeKindBox = new ComboBox<>();
		typeKindBox.getItems().addAll(TypeKindEnum.getAll());
		Button submitButton = new Button(Specifications.getInstance().getResources().getString("ok"));
		submitButton.setOnAction(e->{
			itemType.setTypeName(typeNameTF.getText());
			itemType.setTypeKind(typeKindBox.getValue().getEnumValue());
			System.out.println(itemType.getTypeName() + "-" + itemType.getTypeKind());
			storeController.writeItemTypeToDatabase(itemType);
		});
		newItemTypePane.getChildren().addAll(typeNameTF, typeKindBox, submitButton);
		GridPane.setConstraints(typeNameTF, 0, 0);
		GridPane.setConstraints(typeKindBox, 0, 1);
		GridPane.setConstraints(submitButton, 0, 2);
	}
}
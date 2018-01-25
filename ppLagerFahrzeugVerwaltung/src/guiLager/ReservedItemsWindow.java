package guiLager;

import java.util.ArrayList;

import application.Specifications;
import controller.StoreController;
import databaseLager.Queries;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Item;
import model.ItemReservation;

/**
 * 
 * @author Marcus Zitzelsberger
 *
 */

public class ReservedItemsWindow extends Stage{
	private Queries queries = new Queries();
	private StoreController storeController;;
	private GridPane centerGridPane;
	private ContainerPane containerPane;
	private ArrayList<ItemReservation> reservedItemsList;
	
	public ReservedItemsWindow(ContainerPane containerPane, StoreController storeController) {
		this.storeController = storeController;
		this.containerPane = containerPane;
		setTitle(Specifications.getInstance().getResources().getString("reservedItems"));
		reservedItemsList = new ArrayList<>(queries.getItemReservationsByUserID(
				Specifications.getInstance().getCurrentUser().getUser().getUserId()));
		BorderPane reservedItemsPane = new BorderPane();
		centerGridPane = new GridPane();
		if(!reservedItemsList.isEmpty()) {
			setUpHeadlines();
		}
		reservedItemsPane.setCenter(initCenter());
		Scene reservedItemsScene = new Scene(reservedItemsPane, 400, 320);
		setScene(reservedItemsScene);
		initModality(Modality.APPLICATION_MODAL);
	}
	
	private void setUpHeadlines() {
		Text nameText = new Text(Specifications.getInstance().getResources().getString("name"));
		Text endDateText = new Text(Specifications.getInstance().getResources().getString("endDate"));
		Text actionText = new Text(Specifications.getInstance().getResources().getString("action"));
		centerGridPane.getChildren().addAll(nameText, endDateText, actionText);
		centerGridPane.setAlignment(Pos.CENTER);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
	    column1.setHgrow(Priority.ALWAYS);
	    column1.setHalignment(HPos.CENTER);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(50);
	    column2.setHgrow(Priority.ALWAYS);
	    column2.setHalignment(HPos.CENTER);
	    ColumnConstraints column3 = new ColumnConstraints();
	    column3.setPercentWidth(50);
	    column3.setHgrow(Priority.ALWAYS);
	    column3.setHalignment(HPos.CENTER);
	    centerGridPane.getColumnConstraints().addAll(column1, column2, column3);
		GridPane.setConstraints(nameText, 0, 0);
		GridPane.setConstraints(endDateText, 1, 0);
		GridPane.setConstraints(actionText, 2, 0);
		centerGridPane.setGridLinesVisible(true);
	}
	
	private Node initCenter() {
		ScrollPane centerScrollPane = new ScrollPane();
		if(reservedItemsList.isEmpty()) {
			Text noItemsText = new Text(Specifications.getInstance().getResources().getString("noReservedItems"));
			centerGridPane.getChildren().addAll(noItemsText);
			GridPane.setConstraints(noItemsText, 0, 0);
			GridPane.setMargin(noItemsText, new Insets(10.0));
		} else {
			for(int i = 0; i < reservedItemsList.size(); i++) {
				ItemReservation ires = reservedItemsList.get(i);
				Item reservedItem = queries.getItemByItemID(ires.getItemId());
				Text endDateText = new Text(ires.getEnddate().toString());
				Text itemNameText = new Text(reservedItem.getName());
				Button returnItemButton = new Button(Specifications.getInstance().getResources().getString("returnItem"));
				returnItemButton.setOnAction(e->{
					queries.deleteItemReservation(ires);
					reservedItem.setLent((byte)0);
					storeController.updateItem(reservedItem);
					containerPane.initCenter();
					centerGridPane.getChildren().removeAll(itemNameText, endDateText, returnItemButton);
				});
				centerGridPane.getChildren().addAll(itemNameText, endDateText, returnItemButton);
				GridPane.setConstraints(itemNameText, 0, i + 1);
				GridPane.setConstraints(endDateText, 1, i + 1);
				GridPane.setConstraints(returnItemButton, 2, i + 1);
				GridPane.setMargin(itemNameText, new Insets(10.0));
				GridPane.setMargin(endDateText, new Insets(10.0));
				GridPane.setMargin(returnItemButton, new Insets(10.0));
			}
		}
		centerScrollPane.setContent(centerGridPane);
		return centerScrollPane;
	}
}

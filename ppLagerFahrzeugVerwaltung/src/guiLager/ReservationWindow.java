package guiLager;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import application.Specifications;
import controller.StoreController;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Item;
import model.ItemReservation;

/**
 * 
 * @author Marcus Zitzelsberger, Markus Exner
 *
 */

public class ReservationWindow extends Stage {
	
	private Item item;
	private StoreController storeController;
	private ContainerPane containerPane;

	
	public ReservationWindow(Item item, StoreController storeController, ContainerPane containerPane) {
		this.item = item;
		this.storeController = storeController;
		this.containerPane = containerPane;
		setTitle(Specifications.getInstance().getResources().getString("reservation"));
		initModality(Modality.APPLICATION_MODAL);
		BorderPane reservationPane = new BorderPane();
		reservationPane.setCenter(initCenter());
		Scene reservationScene = new Scene(reservationPane, 450, 150);
		setScene(reservationScene);
	}
	
	private Node initCenter() {
		GridPane centerGridPane = new GridPane();
		ToggleGroup toggleGroup = new ToggleGroup();
		RadioButton reserveRadioButton = new RadioButton(Specifications.getInstance().getResources().getString("reserve"));
		reserveRadioButton.setSelected(true);
		RadioButton rentRadioButton = new RadioButton(Specifications.getInstance().getResources().getString("rent"));
		toggleGroup.getToggles().addAll(reserveRadioButton, rentRadioButton);
		Text beginDateText = new Text(Specifications.getInstance().getResources().getString("beginDate") + ":");
		Text endDateText = new Text(Specifications.getInstance().getResources().getString("endDate") + ":");
		DatePicker beginDateDatePicker = new DatePicker();
		DatePicker endDateDatePicker = new DatePicker();
		Button okButton = new Button("ok");
		okButton.setOnAction(e -> {
			//add Code to reserve
			if ((beginDateDatePicker.getValue() == null || endDateDatePicker.getValue() == null)) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(Specifications.getInstance().getResources().getString("wrongDate"));
				alert.setHeaderText(Specifications.getInstance().getResources().getString("wrongDateHeader"));
				alert.setContentText(Specifications.getInstance().getResources().getString("emptyDate"));
				alert.showAndWait();
			} 
			else if (beginDateDatePicker.getValue().isAfter(endDateDatePicker.getValue())) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle(Specifications.getInstance().getResources().getString("wrongDate"));
				alert.setHeaderText(Specifications.getInstance().getResources().getString("wrongDateHeader"));
				alert.setContentText(Specifications.getInstance().getResources().getString("wrongDateContent"));
				alert.showAndWait();
			} else {
				// Begin date
				Calendar startCalendar = Calendar.getInstance();
				LocalDate startDate = beginDateDatePicker.getValue();
				startCalendar.set(startDate.getYear(), startDate.getMonthValue() - 1, startDate.getDayOfMonth());
				Date theStartDate = startCalendar.getTime();
				
				// End date
				Calendar endCalendar = Calendar.getInstance();
				LocalDate endDate = endDateDatePicker.getValue();
				endCalendar.set(endDate.getYear(), endDate.getMonthValue() - 1, endDate.getDayOfMonth());
				Date theEndDate = endCalendar.getTime();
				
				item.setLent((byte)1);
				ItemReservation itemReservation = new ItemReservation();
				itemReservation.setItemId(item.getItemId());
				itemReservation.setStartdate(theStartDate);
				itemReservation.setEnddate(theEndDate);
				itemReservation.setUserId(Specifications.getInstance().getCurrentUser().getUser().getUserId());
				storeController.updateItem(item);
				storeController.insertReservation(itemReservation);
				this.containerPane.initCenter();
				this.close();
			}
		});
		centerGridPane.getChildren().addAll(reserveRadioButton, rentRadioButton, beginDateText,
				endDateText, beginDateDatePicker, endDateDatePicker, okButton);
		GridPane.setConstraints(reserveRadioButton, 0, 0);
		GridPane.setConstraints(beginDateText, 1, 0);
		GridPane.setConstraints(beginDateDatePicker, 2, 0);
		GridPane.setConstraints(endDateText, 1, 1);
		GridPane.setConstraints(endDateDatePicker, 2, 1);
		GridPane.setConstraints(rentRadioButton, 0, 2);
		GridPane.setConstraints(okButton, 2, 2);
		GridPane.setMargin(reserveRadioButton, new Insets(10.0));
		GridPane.setMargin(beginDateText, new Insets(10.0));
		GridPane.setMargin(beginDateDatePicker, new Insets(10.0));
		GridPane.setMargin(endDateText, new Insets(10.0));
		GridPane.setMargin(endDateDatePicker, new Insets(10.0));
		GridPane.setMargin(rentRadioButton, new Insets(10.0));
		GridPane.setMargin(okButton, new Insets(10.0));
		return centerGridPane;
	}
}

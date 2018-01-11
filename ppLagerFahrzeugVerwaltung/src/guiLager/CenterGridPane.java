package guiLager;

import application.Specifications;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Item;

public class CenterGridPane extends GridPane {

	private int rowCounter = 1;
	
	public CenterGridPane() {
		setUpHeadlines();
	}
	
	private void setUpHeadlines() {
		Text pictureText = new Text(Specifications.getInstance().getResources().getString("picture"));
		Text descriptionText = new Text(Specifications.getInstance().getResources().getString("description"));
		Text dateText = new Text(Specifications.getInstance().getResources().getString("date"));
		Text actionText = new Text(Specifications.getInstance().getResources().getString("action"));
		getChildren().addAll(pictureText, descriptionText, dateText, actionText);
		setAlignment(Pos.CENTER);
		ColumnConstraints column1 = new ColumnConstraints(100, 150, 300);
	    column1.setHgrow(Priority.ALWAYS);
	    column1.setHalignment(HPos.CENTER);
	    ColumnConstraints column2 = new ColumnConstraints(100, 150, 300);
	    column2.setHgrow(Priority.ALWAYS);
	    column2.setHalignment(HPos.CENTER);
		ColumnConstraints column3 = new ColumnConstraints(100, 150, 300);
	    column3.setHgrow(Priority.ALWAYS);
	    column3.setHalignment(HPos.CENTER);
	    ColumnConstraints column4 = new ColumnConstraints(100, 150, 300);
	    column4.setHgrow(Priority.ALWAYS);
	    column4.setHalignment(HPos.CENTER);
	    getColumnConstraints().addAll(column1, column2, column3, column4);
		GridPane.setConstraints(pictureText, 0, 0);
		GridPane.setConstraints(descriptionText, 1, 0);
		GridPane.setConstraints(dateText, 2, 0);
		GridPane.setConstraints(actionText, 3, 0);
		setGridLinesVisible(true);
	}
	
	public void addItemToList(Item item) {
		ImageView image = new ImageView(new Image(item.getItemPicture()));
		Text description = new Text(item.getDescription());
		Text date = new Text(item.getEntrydate().toString());
		VBox buttonsVBox = new VBox(10);
		ReservationButton reservationButton = new ReservationButton(Specifications.getInstance().getResources().getString("reserve"), item);
		Button removeItemButton = new Button(Specifications.getInstance().getResources().getString("removeItem"));
		removeItemButton.setVisible(false); // add visibility with UserRights
		buttonsVBox.getChildren().addAll(reservationButton, removeItemButton);
		getChildren().addAll(image, description, date, buttonsVBox);
		GridPane.setConstraints(image, 0, rowCounter);
		GridPane.setConstraints(description, 1, rowCounter);
		GridPane.setConstraints(date, 2, rowCounter);
		GridPane.setConstraints(reservationButton, 3, rowCounter);
	}
}
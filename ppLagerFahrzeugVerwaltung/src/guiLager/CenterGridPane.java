package guiLager;


import application.Specifications;
import controller.StoreController;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.Item;

public class CenterGridPane extends GridPane {

	private int rowCounter = 1;
	private StoreController storeController;
	private ContainerPane containerPane;
	
	public CenterGridPane(ContainerPane containerPane) {
		this.storeController = new StoreController();
		this.containerPane = containerPane;
		this.setPrefSize(950, 0);
		this.setMaxSize(Region.USE_COMPUTED_SIZE, 0);
		setUpHeadlines();
	}
	
	private void setUpHeadlines() {
		Text itemNameText = new Text(Specifications.getInstance().getResources().getString("name"));
		Text pictureText = new Text(Specifications.getInstance().getResources().getString("picture"));
		Text descriptionText = new Text(Specifications.getInstance().getResources().getString("description"));
		Text dateText = new Text(Specifications.getInstance().getResources().getString("date"));
		Text actionText = new Text(Specifications.getInstance().getResources().getString("action"));
		Text lentStatusText = new Text(Specifications.getInstance().getResources().getString("isLent"));
		getChildren().addAll(itemNameText, pictureText, descriptionText, dateText, actionText, lentStatusText);
		setAlignment(Pos.CENTER);
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(15);
	    column1.setHgrow(Priority.ALWAYS);
	    column1.setHalignment(HPos.CENTER);
	    ColumnConstraints column2 = new ColumnConstraints();
	    column2.setPercentWidth(20);
	    column2.setHgrow(Priority.ALWAYS);
	    column2.setHalignment(HPos.CENTER);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setPercentWidth(20);
	    column3.setHgrow(Priority.ALWAYS);
	    column3.setHalignment(HPos.CENTER);
	    ColumnConstraints column4 = new ColumnConstraints();
	    column4.setPercentWidth(15);
	    column4.setHgrow(Priority.ALWAYS);
	    column4.setHalignment(HPos.CENTER);
	    ColumnConstraints column5 = new ColumnConstraints();
	    column5.setPercentWidth(20);
	    column5.setHgrow(Priority.ALWAYS);
	    column5.setHalignment(HPos.CENTER);
	    ColumnConstraints column6 = new ColumnConstraints();
	    column6.setPercentWidth(10);
	    column6.setHgrow(Priority.ALWAYS);
	    column6.setHalignment(HPos.CENTER);
	    getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6);
		GridPane.setConstraints(itemNameText, 0, 0);
		GridPane.setConstraints(descriptionText, 1, 0);
		GridPane.setConstraints(dateText, 2, 0);
		GridPane.setConstraints(actionText, 3, 0);
		GridPane.setConstraints(pictureText, 4, 0);
		GridPane.setConstraints(lentStatusText, 5, 0);
		setGridLinesVisible(true);
	}
	
	public void addItemToList(Item item, ContainerPane containerPane) {
		// Check remove button depending on role
		int currentUserRoleID = Specifications.getInstance().getCurrentUser().getRole().getRoleId();
		String itemPicture = "";
		itemPicture = item.getItemPicture();
		ImageView image = new ImageView();
		if (!itemPicture.isEmpty()) {
			image = new ImageView(new Image(itemPicture));
		}
		Text name = new Text(item.getName());
		Text description = new Text(item.getDescription());
		description.setWrappingWidth(180);
		Text date = new Text(item.getEntrydate().toString());
		CheckBox lentCheckBox = new CheckBox();
		lentCheckBox.setSelected(item.getLent() == 1);
		lentCheckBox.setDisable(true);
		VBox buttonsVBox = new VBox(10);
		ReservationButton reservationButton = new ReservationButton(Specifications.getInstance().getResources().getString("reserve"), item, this.containerPane);
		Button removeItemButton = new Button(Specifications.getInstance().getResources().getString("removeItem"));
		
		removeItemButton.setVisible(currentUserRoleID == 1 || currentUserRoleID == 2); 
		removeItemButton.setOnAction(e -> {
			storeController.removeItemFromDatabase(item);
			containerPane.initCenter();
		});
		buttonsVBox.getChildren().addAll(reservationButton, removeItemButton);
		getChildren().addAll(name, image, description, date, buttonsVBox, lentCheckBox);
		GridPane.setConstraints(name, 0, rowCounter);
		GridPane.setConstraints(description, 1, rowCounter);
		GridPane.setConstraints(date, 2, rowCounter);
		GridPane.setConstraints(buttonsVBox, 3, rowCounter);
		GridPane.setConstraints(image, 4, rowCounter);
		GridPane.setConstraints(lentCheckBox, 5, rowCounter);
		rowCounter++;
	}
}

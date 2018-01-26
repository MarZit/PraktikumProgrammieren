package guiLager;

import controller.StoreController;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Item;

/**
 * 
 * @author Marcus Zitzelsberger
 *
 */

public class CalendarDay extends StackPane {
	
	private Item takenItem;
	private int dayOfMonth;
	private ContainerPane containerPane;
	private StoreController storeController;
	
	public CalendarDay(int dayOfMonth, ContainerPane containerPane, StoreController storeController) {
		this.containerPane = containerPane;
		this.storeController = storeController;
		this.dayOfMonth = dayOfMonth;
		setUpPane();
		this.setOnMouseClicked(e -> {
			ReservedItemsWindow reservedItemsWindow = new ReservedItemsWindow(this.containerPane, this.storeController);
			reservedItemsWindow.showAndWait();
		});
	}
	
	private void setUpPane() {
		Rectangle background = new Rectangle(30.0, 30.0);
		background.setFill(Color.AQUA);
		background.setStroke(Color.BLACK);
		Text dayText = new Text(String.valueOf(dayOfMonth));
		getChildren().addAll(background, dayText);
	}
	
	public void addTakenItem(Item item) {
		this.takenItem = item;
	}
}

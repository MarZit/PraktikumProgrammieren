package guiLager;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import model.Item;

public class CalendarDay extends StackPane {
	
	Item takenItem;
	int dayOfMonth;
	
	public CalendarDay(int dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
		setUpPane();
	}
	
	private void setUpPane() {
		Rectangle background = new Rectangle(30.0, 30.0);
//		background.setId("background-rect");
		background.setFill(Color.AQUA);
		background.setStroke(Color.BLACK);
		Text dayText = new Text(String.valueOf(dayOfMonth));
		getChildren().addAll(background, dayText);
	}
	
	public void addTakenItem(Item item) {
		this.takenItem = item;
	}
}

package guiLager;

import javafx.scene.control.Button;
import model.Item;

public class ReservationButton extends Button {

	public ReservationButton(String text, Item item) {
		super(text);
		setOnAction(e -> {
			ReservationWindow reservationWindow = new ReservationWindow(item);
			reservationWindow.showAndWait();
		});
	}
}

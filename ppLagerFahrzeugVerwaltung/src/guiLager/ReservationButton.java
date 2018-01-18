package guiLager;

import controller.StoreController;
import javafx.scene.control.Button;
import model.Item;

public class ReservationButton extends Button {
	private StoreController storeController;

	public ReservationButton(String text, Item item) {
		super(text);
		this.storeController = new StoreController();
		setOnAction(e -> {
			ReservationWindow reservationWindow = new ReservationWindow(item, storeController);
			reservationWindow.showAndWait();
		});
	}
}

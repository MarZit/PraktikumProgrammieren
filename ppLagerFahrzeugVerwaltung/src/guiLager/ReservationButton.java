package guiLager;

import controller.StoreController;
import javafx.scene.control.Button;
import model.Item;

public class ReservationButton extends Button {
	private StoreController storeController;
	private ContainerPane containerPane;

	public ReservationButton(String text, Item item, ContainerPane containerPane) {
		super(text);
		this.storeController = new StoreController();
		this.containerPane = containerPane;
		setOnAction(e -> {
			ReservationWindow reservationWindow = new ReservationWindow(item, storeController, this.containerPane);
			reservationWindow.showAndWait();
		});
	}
}

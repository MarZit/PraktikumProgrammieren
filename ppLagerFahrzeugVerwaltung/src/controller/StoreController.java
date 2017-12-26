package controller;

import model.Item;
import databaseLager.Queries;

public class StoreController {
	
//	private Item item;
	private Queries queries;

	
	public StoreController(Item item) {
//		this.item = item;
	}
	
	public void writeItemToDatabase(Item item) {
		
		queries.insertItem(item);
	}
	
	public void removeItemFromDatabase(Item item) {
		queries.deleteItem(item);
	}

	

	
	
}

package controller;

import model.Item;
import modelLager.Store;
import databaseLager.Queries;

public class StoreController {
	
//	private Item item;
	private Queries queries;

	
	public StoreController(Item item) {
//		this.item = item;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return Store.getInstance().getLagerList().toString();
	}
	
	public void writeItemToDatabase(Item item) {
		
		queries.insertItem(item);
	}
	
	public void removeItemFromDatabase(Item item) {
		queries.deleteItem(item);
	}

	

	
	
}

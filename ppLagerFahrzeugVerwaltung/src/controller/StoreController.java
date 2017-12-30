package controller;

import java.util.ArrayList;

import databaseLager.Queries;
import model.Item;
import model.ItemType;


public class StoreController {
	
//	private Item item;
	private Queries queries;

	
	public StoreController() {
//		this.item = item;
//		queries = new Queries();
	}
	
	public void writeItemToDatabase(Item item) {
		queries.insertItem(item);
	}
	
	public void writeItemTypeToDatabase(ItemType itemType) {
		queries.insertItemType(itemType);
	}
	
	public void removeItemFromDatabase(Item item) {
		queries.deleteItem(item);
	}
	
	public ArrayList<ItemType> getItemTypesFromDatabase(){
//		queries.
		return null;
	}

	

	
	
}

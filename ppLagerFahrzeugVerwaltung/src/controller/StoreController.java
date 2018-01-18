package controller;

import java.util.ArrayList;

import application.TypeKindEnum;
import databaseLager.Queries;
import model.Item;
import model.ItemType;


public class StoreController {
	
//	private Item item;
	private Queries queries;

	
	public StoreController() {
//		this.item = item;
		queries = new Queries();
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
	
	public ArrayList<ItemType> getItemKindTypeFromDatabase(TypeKindEnum typeKind) {
		return new ArrayList<ItemType>(queries.getItemTypesByTypeKind(typeKind.getEnumValue()));
	}
	
	public ArrayList<ItemType> getItemTypesFromDatabase(){
		return new ArrayList<ItemType>(queries.getItemTypes());
	}
	
	public ArrayList<Item> getItemsFromDatabase(int itemTypeID) {
		ArrayList<Item> itemList = new ArrayList<Item>(queries.getItemsByItemType(itemTypeID));
		if (itemList.isEmpty()) {
			return new ArrayList<Item>();
		}
		return itemList;
	}
	
	public void updateItem(Item item) {
		queries.updateItem(item);
	}
	
	
}

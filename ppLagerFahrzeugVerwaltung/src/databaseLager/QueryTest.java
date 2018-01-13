package databaseLager;

import java.util.Date;
import java.util.List;

import model.Item;
import model.ItemReservation;
import model.ItemType;
import model.ItemTypeRoleRelation;
import model.ItemTypeRoleRelationPK;
import model.ItemUsed;
import model.Role;
import model.User;



//Klasse dient zum einmaligen Testen aller Queries, Nachdem der Test ausgeführt wurde muss die Datenbank wieder neu gefüllt werden (DatabaseCreator)
public class QueryTest {

	public Queries q = new Queries();
	
	public QueryTest(){
		
		
	}
	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
		Queries q = new Queries();
		
		
		
		// Item Tests
		Item i = q.getItemByItemID(1);
		System.out.println("GetItemByItemID: " + i.getName());
		
		i.setName("Test");
		q.updateItem(i);
		System.out.println("ItemUpdated");
		
		i = q.getItemByItemID(1);
		System.out.println("Wenn Name Test --> update erfolgreich " + i.getName());

		List <Item> itemList = q.getItemsByItemName("VW Golf 7");
		System.out.println("GetItemsByItemName: Anzahl der Items in der List " + itemList.size());
		
		
		itemList = q.getItemsByItemKind(1);
		System.out.println("GetItemsByItemKind: Anzahl der Items in der List " + itemList.size());
		
		itemList = q.getItemsByItemType(2);
		System.out.println("GetItemsByItemType: Anzahl der Items in der List " + itemList.size());
		
		
		
		Item newItem = new Item();
		newItem.setDescription("Beschreibung");
		newItem.setName("ItemName");
		newItem.setTypeId(1);
		newItem.setItemId(100);
		newItem.setEntrydate(new Date(2017,12,21));
		newItem.setOut(false);
		newItem.setLent(false);
		newItem.setItemPicture("picture");
		q.insertItem(newItem);
		System.out.println("ItemInserted");
		
		
		q.deleteItem(i);
		System.out.println("ItemDeleted");
		
		try{
		i = q.getItemByItemID(1);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace() + " Wenn zu lesen --> Löschen erfolgreich");
		}
		
		q.getItems();
		System.out.println("GetItems: Anzahl der aller Items in der Datenbank" + itemList.size());
		
		
		
		//Role Tests
		List <Role> roleList = q.getRoles();
		System.out.println("getRoles: Anzahl alles Roles in der Datenbank " + roleList.size());
		
		Role r = q.getRoleByRoleID(1);
		System.out.println("getRoleByRoleID:" + r.getRoleName());
		
		r.setRoleName("NewRoleTest");
		
		q.updateRole(r);
		System.out.println("RoleUpdated");
		
		r = q.getRoleByRoleID(1);
		System.out.println("Wenn Name NewRoleTest --> Update erfolgreich:" + r.getRoleName());
		
		r = new Role();
		r.setRoleId(1500);
		r.setRoleName("TestRole");
		
		q.insertRole(r);
		
		r = q.getRoleByRoleID(1500);
		System.out.println("Wenn 1500 --> Insert erfolgreich: " + r.getRoleId());
		
		q.deleteRole(r);
		
		try{
			r = q.getRoleByRoleID(1500);
			}
			catch(Exception e){
				System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
		}
		
		
		//User Tests
		List <User> userList = q.getUsers();
		System.out.println("getRoles: Anzahl alles User in der Datenbank " + userList.size());
		
		userList = q.getUsersByRoleID(1);
		System.out.println("getRoles: Anzahl alles Admin User in der Datenbank " + userList.size());
		
		User u = q.getUserByUserID(1);
		System.out.println("getUserByUserID1: " + u.getFirstName());
		
		

		u.setUsername("TestName");
		
		q.updateUser(u);
		
		u = q.getUserByUserName("TestName");
		System.out.println("getUserByUserName: Wenn TestName --> Update erfolgreich: " + u.getUsername());
		
		
		u = new User();
		u.setFirstName("InsertUser");
		u.setLastName("Last");
		u.setEmail("testmail");
		u.setRole(1);
		u.setUsername("username");
		u.setUserId(1500);
		u.setPassword("password");
		
		q.insertUser(u);
		
		u = q.getUserByUserID(1500);
		System.out.println("Wenn 1500 --> Insert erfolgreich: " + u.getUserId());
		
		q.deleteUser(u);
		
		System.out.println("UserDeleted");
		
		try{
		u = q.getUserByUserID(1500);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
		}
		
		
		//ItemType Tests
		
		ItemType type = new ItemType();
		type = q.getItemTypeByTypeId(1);
		System.out.println("getITemTypeByTypeId1: " + type.getTypeName());
		
		type.setTypeName("TestName");
		q.updateItemType(type);
		
		type = q.getItemTypeByTypeId(1);
		System.out.println("Wenn TestName --> update erfolgreich " + type.getTypeName());
		
		type = new ItemType();
		type.setTypeId(1500);
		type.setTypeKind(4);
		type.setTypeName("TestName2");
		
		q.insertItemType(type);
		
		type = q.getItemTypeByTypeId(1500);
		System.out.println("Wenn TestName2 --> insert erfolgreich: " + type.getTypeName());
		
		
		q.deleteItemType(type);
		
		System.out.println("ItemTypeDeleted");
		
		try{
		type = q.getItemTypeByTypeId(1500);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
		}
		
		List <ItemType> typeList = q.getItemTypesByTypeKind(1);
		System.out.println("GetItemTypesByTypeKind: Anzahl alles ItemTypes mit ItemKind 1" + typeList.size());
		
		
		//ItemTypeRoleRelation Tests
		
		ItemTypeRoleRelation typeRole = new ItemTypeRoleRelation();	
		ItemTypeRoleRelationPK pk = new ItemTypeRoleRelationPK();
		pk.setRoleId(1);
		pk.setTypeId(1);
		typeRole.setId(pk);
		
		q.insertItemTypeRoleRelation(typeRole);
		typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
		
		System.out.println("getItemTypeRoleRelationsByRoleAndTypeId(1, 1), wenn vorhanden insert erfolgreich" + typeRole.getId());

		
		List <ItemTypeRoleRelation> typeRoleList = q.getItemTypeRoleRelationsByRoleId(1);
		
		System.out.println("Liste mit RoleID 1: " + typeRoleList.size());
		
		typeRoleList = q.getItemTypeRoleRelationsByTypeId(1);
		
		System.out.println("Liste mit TypeID 1: " + typeRoleList.size());
		
		q.deleteItemTypeRoleRelation(typeRole);
		
		System.out.println("Delete erfolgreich");
		
		
		try{
			typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
			}
			catch(Exception e){
				System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
			}
		
		
		//ItemUsed Tests
		
		ItemUsed used = new ItemUsed();
		used.setItemId(1);
		used.setTypeId(2);
		used.setUserId(3);
		used.setDate(new Date(117, 11, 13));
		
		q.useItem(used);
		
		used = new ItemUsed();
		used = q.getItemUsedByItemId(1);
		System.out.println("Wenn 13.12.2017: getItemUsedByItemId funktioniert + insert funktioniert: " + used.getDate());
		
		used.setDate(new Date(117, 11, 14));
		q.updateItemUsed(used);
		used = q.getItemUsedByItemId(1);
		System.out.println("Wenn 14.12.2017: update funktioniert: " + used.getDate());
		
		List <ItemUsed> usedList;
		usedList = q.getItemUsedByTypeId(2);
		System.out.println("Größe von getItemUsedByTypeId muss 1 sein: " + usedList.size());
		
		
		usedList = q.getItemUsedByUserId(3);
		System.out.println("Größe von getItemUsedByUserId muss 1 sein: " + usedList.size());
		
		//DateQueries funktionieren nicht
		usedList = q.getItemUsedByDate(new Date(117, 11, 14));
		System.out.println("Größe von getItemUsedByDate muss 1 sein: " + usedList.size());
		
		usedList = q.getItemUsedBetweenDates(new Date(117, 11, 10), new Date(117, 11, 20));
		System.out.println("Größe von getItemUsedBetweenDate muss 1 sein: " + usedList.size());
		
		
		q.deleteItemUsed(used);
		
		try{
			used = q.getItemUsedByItemId(1);
			}
			catch(Exception e){
				System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
			}
		
		
		//ItemReservation Tests
		
 		ItemReservation res = new ItemReservation();
		res.setUserId(1);
		res.setItemId(1);
		res.setStartdate(new Date(117, 11, 13));
		res.setEnddate(new Date(117, 11, 20));
		res.setReservationId(1);
		res.setOpen(true);
		res.setOverrun(true);
		
		q.insertReservation(res);
		
		
		res = q.getItemReservationByReservationID(1);
		System.out.println("getItemReservationByReservationID(1): Wenn 1 insert erfolgreich" + res.getReservationId());
		
		
		res.setItemId(2);
		q.updateReservation(res);
		res = q.getItemReservationByReservationID(1);
		System.out.println("Wenn 2 update erfolgreich" + res.getItemId());
		
		
		List <ItemReservation> resList;
		resList = q.getItemReservationsByItemID(2);
		System.out.println("getItemReservationsByItemID(1), Größe muss 1 sein: " + resList.size());
		
		
		resList = q.getItemReservationsByUserID(1);
		System.out.println("getItemReservationsByUserID(1), Größe muss 1 sein: " + resList.size());
		

		resList = q.getItemReservationsBySingleDate(new Date(117, 11, 17));
		System.out.println("getItemReservationsBySingleDate(), Größe muss 1 sein: " + resList.size());
		
		resList = q.getItemReservationsBetweenDates(new Date(117, 11, 12), new Date(117, 11, 23));
		System.out.println("getItemReservationsBetweenDates(), Größe muss 1 sein: " + resList.size());
		
		
		q.deleteItemReservation(res);
		
		try{
			res = q.getItemReservationByReservationID(1);
			}
			catch(Exception e){
				System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
			}
		
				
	}

}

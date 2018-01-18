package databaseLager;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import  javax.persistence.NoResultException;

import org.junit.Test;

import model.Item;
import model.ItemReservation;
import model.ItemType;
import model.ItemTypeRoleRelation;
import model.ItemTypeRoleRelationPK;
import model.ItemUsed;
import model.Role;
import model.User;



/**
 * Klasse dient zum einmaligen Testen aller Queries mit den Standarddaten aus dem DatabaseCreator
 * @author Julian
 *
 */

//Mit anderen hinzugefügten Daten ist der Test nicht aussagekräftig
//Nach Ausführen der Klasse werden die geänderten Daten wiederhergestellt
public class QueryTest {

	
	public QueryTest(){
		
	}
	
				
		
	
		/**JUnit Tests für alle Item-Queries*/
		@SuppressWarnings("deprecation")
		@Test
		public void itemTest(Queries q){
		Item i = q.getItemByItemID(1);
		assertEquals("GetItemByItemID: Name muss VW Golf 7 sein", "VW Golf 7", i.getName());
		
		
		i.setName("Test");
		q.updateItem(i);
		
		i = q.getItemByItemID(1);
		assertEquals("Update: Name muss Test sein", "Test", i.getName());
		

		List <Item> itemList = q.getItemsByItemName("Audi A3");
		assertEquals("GetItemsByItemName: Anzahl der Items muss 3 sein", 3, itemList.size());
		
		
		itemList = q.getItemsByItemKind(1);
		assertEquals("GetItemsByItemKind: Anzahl der Items in der List muss 9 sein", 9, itemList.size());
		
		
		itemList = q.getItemsByItemType(2);
		assertEquals("GetItemsByItemType: Anzahl der Items muss 3 sein", 3, itemList.size());
			
		
		Item newItem = new Item();
		newItem.setDescription("Beschreibung");
		newItem.setName("ItemName");
		newItem.setTypeId(1);
		newItem.setItemId(100);
		newItem.setEntrydate(new Date(117,11,21));
		newItem.setOut(false);
		newItem.setLent(false);
		newItem.setItemPicture("picture");
		q.insertItem(newItem);
		System.out.println("ItemInserted");
		
		i = q.getItemByItemID(100);
		assertEquals("Insert: Name muss ItemName sein", "ItemName", i.getName());
		
		itemList = q.getItems();
		assertEquals("GetItems: Anzahl aller Items muss 35 sein", 35, itemList.size());
		
		try{
		itemDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		
		restoreItem(q);

		}
		
		public void restoreItem(Queries q){
		Item i = q.getItemByItemID(1);
		i.setName("VW Golf 7");
		q.updateItem(i);
		}
		
		// Methode für Delete Item
		@Test(expected = NoResultException.class)
		public void itemDeleteTest(Queries q){
		Item i = q.getItemByItemID(100);
		q.deleteItem(i);
		System.out.println("ItemDeleted");
		
		i = q.getItemByItemID(100);
		}
		
		/**JUnit Tests für alle Role-Queries*/
		@Test
		public void roleTests(Queries q){
		List <Role> roleList = q.getRoles();
		assertEquals("GetRoles: Anzahl aller Roles muss e sein", 3, roleList.size());

		
		Role r = q.getRoleByRoleID(1);
		assertEquals("GetRoleByRoleId: Name muss admin sein", "admin", r.getRoleName());

		
		r.setRoleName("Test");
		
		q.updateRole(r);
		System.out.println("RoleUpdated");
		
		r = q.getRoleByRoleID(1);
		assertEquals("Update: Name muss Test sein", "Test", r.getRoleName());
		
		
		r = new Role();
		r.setRoleId(1500);
		r.setRoleName("Test");
		
		q.insertRole(r);
		
		r = q.getRoleByRoleID(1500);
		assertEquals("Insert: Name muss Test sein", "Test", r.getRoleName());

		try{
		roleDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		
		restoreRole(q);
		
		}
		
		public void restoreRole(Queries q){
		Role r = q.getRoleByRoleID(1);
		r.setRoleName("admin");
		q.updateRole(r);
		}
		
		//Methode für Delete Role
		@Test(expected = NoResultException.class)
		public void roleDeleteTest(Queries q){
		Role r = q.getRoleByRoleID(1500);
		q.deleteRole(r);
		
		r = q.getRoleByRoleID(1500);

		}
		
		
		//JUnit Tests für alle User-Queries
		@Test
		public void userTests(Queries q){
		List <User> userList = q.getUsers();
		assertEquals("GetUsers: Anzahl aller User muss 6 sein", 6, userList.size());

		
		userList = q.getUsersByRoleID(1);
		assertEquals("GetUsersByRoleID: Anzahl muss 5 sein", 5, userList.size());

		
		User u = q.getUserByUserID(5);
		assertEquals("GetUserByUserID: Name muss Admin sein", "Admin", u.getUsername());
		
		

		u.setUsername("Test");
		
		q.updateUser(u);
		
		u = q.getUserByUserName("Test");
		assertEquals("Update: Name muss Test sein", "Test", u.getUsername());
		
		
		u = new User();
		u.setFirstName("Test");
		u.setLastName("Last");
		u.setEmail("testmail");
		u.setRole(1);
		u.setUsername("Test");
		u.setUserId(1500);
		u.setPassword("password");
		
		q.insertUser(u);
		
		u = q.getUserByUserID(1500);
		assertEquals("Insert: Name muss Test sein", "Test", u.getUsername());

		try{
		userDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		
		restoreUser(q);
		
		}
		
		public void restoreUser(Queries q){
		User u = q.getUserByUserID(5);
		u.setUsername("Admin");
		q.updateUser(u);
		}
		
		//Methode für Delete User
		@Test(expected = NoResultException.class)
		public void userDeleteTest(Queries q){
		
		User u = q.getUserByUserID(1500);
		q.deleteUser(u);
		
		System.out.println("UserDeleted");
		
		u = q.getUserByUserID(1500);

		}
		
		
		/**JUnit Tests für alle ItemType-Queries*/
		@Test
		public void itemTypeTests(Queries q){
		
		List <ItemType> typeList = q.getItemTypes();
		assertEquals("GetItemTypes: Anzahl aller ItemTypes muss 7 sein", 7, typeList.size());
		
		ItemType type = new ItemType();
		type = q.getItemTypeByTypeId(1);
		assertEquals("GetItemTypeByTypeId: Name muss PKW sein", "PKW", type.getTypeName());
		
		
		type.setTypeName("Test");
		q.updateItemType(type);
		
		type = q.getItemTypeByTypeId(1);
		assertEquals("Update: Name muss Test sein", "Test", type.getTypeName());

		
		type = new ItemType();
		type.setTypeId(1500);
		type.setTypeKind(4);
		type.setTypeName("TestName");
		
		q.insertItemType(type);
		
		type = q.getItemTypeByTypeId(1500);
		assertEquals("Insert: Name muss Test sein", "TestName", type.getTypeName());
		
		typeList = q.getItemTypesByTypeKind(1);
		assertEquals("GetItemTypesByTypeKind: Anzahl muss 2 sein", 2, typeList.size());
		
		try{
		itemTypeDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		
		restoreItemType(q);
		
		}
		
		public void restoreItemType(Queries q){
		ItemType i = q.getItemTypeByTypeId(1);
		i.setTypeName("PKW");
		q.updateItemType(i);
		}
		
		//Methode für Delete ItemType
		@Test(expected = NoResultException.class)
		public void itemTypeDeleteTest(Queries q){
		
		ItemType type = q.getItemTypeByTypeId(1500);
		q.deleteItemType(type);
		
		System.out.println("ItemTypeDeleted");
		
		try{
		type = q.getItemTypeByTypeId(1500);
		}
		catch(Exception e){
			System.out.println(e.getStackTrace() + " Wenn nullPointer --> Löschen erfolgreich");
		}
		}
		
		/**JUnit Tests für alle ItemTypeRoleRelation-Queries*/
		@Test
		public void itemTypeRoleRelationTests(Queries q){
		
		ItemTypeRoleRelation typeRole = new ItemTypeRoleRelation();	
		ItemTypeRoleRelationPK pk = new ItemTypeRoleRelationPK();
		pk.setRoleId(1);
		pk.setTypeId(1);
		typeRole.setId(pk);
		
		q.insertItemTypeRoleRelation(typeRole);
		typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
		assertEquals("getItemTypeRoleRelationsByRoleAndType und Insert: ID muss pk sein", pk, typeRole.getId());
		
		
		List <ItemTypeRoleRelation> typeRoleList = q.getItemTypeRoleRelationsByRoleId(1);
		assertEquals("getItemTypeRoleRelationsByRoleId: Anzahl muss 1 sein", 1, typeRoleList.size());
		
		
		typeRoleList = q.getItemTypeRoleRelationsByTypeId(1);
		assertEquals("getItemTypeRoleRelationsByTypeId: Anzahl muss 1 sein", 1, typeRoleList.size());
		
		
		
		try{
		itemTypeRoleRelationDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		}
		
		
		//Methode für Delete ItemTypeRoleRelation
		@Test(expected = NoResultException.class)
		public void itemTypeRoleRelationDeleteTest(Queries q){
		
		ItemTypeRoleRelation typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
		q.deleteItemTypeRoleRelation(typeRole);
		
		System.out.println("Delete erfolgreich");
		
		typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
		}

		
		/**JUnit Tests für alle ItemUsed-Queries*/
		@SuppressWarnings("deprecation")
		@Test
		public void itemUsedTests(Queries q){
		ItemUsed used = new ItemUsed();
		used.setItemId(1);
		used.setTypeId(2);
		used.setUserId(3);
		used.setDate(new Date(117, 11, 13));
		
		q.useItem(used);
		
		used = new ItemUsed();
		used = q.getItemUsedByItemId(1);
		assertEquals("getItemUsedByItemId und Insert (UseItem): ItemId muss 1 sein", 1, used.getItemId());
		
		
		used.setDate(new Date(117, 11, 14));
		q.updateItemUsed(used);
		used = q.getItemUsedByItemId(1);
		assertEquals("Update: Datum muss 14.12.2017 sein", new Date(117, 11, 14), used.getDate());

		
		List <ItemUsed> usedList;
		usedList = q.getItemUsedByTypeId(2);
		assertEquals("GetItemUsedByTypeId: Anzahl muss 1 sein", 1, usedList.size());
		
		
		
		usedList = q.getItemUsedByUserId(3);
		assertEquals("GetItemUsedByUserId: Anzahl muss 1 sein", 1, usedList.size());
		
		usedList = q.getItemUsedByDate(new Date(117, 11, 14));
		assertEquals("GetItemUsedByDate: Anzahl muss 1 sein", 1, usedList.size());
		
		usedList = q.getItemUsedBetweenDates(new Date(117, 11, 10), new Date(117, 11, 20));
		assertEquals("GetItemUsedBetweenDates: Anzahl muss 1 sein", 1, usedList.size());
		
		try{
		itemUsedDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		}
		
		
		//Methode für Delete ItemUsed
		@Test(expected = NoResultException.class)
		public void itemUsedDeleteTest(Queries q){
		ItemUsed used = q.getItemUsedByItemId(1);
		q.deleteItemUsed(used);
		
		used = q.getItemUsedByItemId(1);

		}
		
		/**JUnit Tests für alle ItemReservation-Queries*/
		@SuppressWarnings("deprecation")
		@Test
		public void itemReservationTests(Queries q){
		
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
		assertEquals("GetItemReservationsByReservationID und Insert: ItemId muss 1 sein", 1, res.getItemId());
		
		
		res.setItemId(2);
		q.updateReservation(res);
		res = q.getItemReservationByReservationID(1);
		assertEquals("Update: ItemId muss 2 sein", 2, res.getItemId());
		
		
		List <ItemReservation> resList;
		resList = q.getItemReservationsByItemID(2);
		assertEquals("GetItemReservationsByItemID: Größe muss 1 sein", 1, resList.size());
		
		
		resList = q.getItemReservationsByUserID(1);
		assertEquals("GetItemReservationsByUserID: Größe muss 1 sein", 1, resList.size());
		

		resList = q.getItemReservationsBySingleDate(new Date(117, 11, 17));
		assertEquals("GetItemReservationsBySingleDate: Größe muss 1 sein", 1, resList.size());
		
		resList = q.getItemReservationsBetweenDates(new Date(117, 11, 12), new Date(117, 11, 23));
		assertEquals("GetItemReservationsBetweenDates: Größe muss 1 sein", 1, resList.size());
		
		try{
		itemReservationDeleteTest(q);
		}
		catch(NoResultException e){
			
		}
		
		}
		
		//Methode für Delete ItemReservation
		@Test(expected = NoResultException.class)
		public void itemReservationDeleteTest(Queries q){
		
		ItemReservation res = q.getItemReservationByReservationID(1);
		q.deleteItemReservation(res);
		
		res = q.getItemReservationByReservationID(1);

		}
		
		public static void main (String [] args){
			
			QueryTest test = new QueryTest();
			Queries q = new Queries();
			
			test.itemTest(q);
			test.itemReservationTests(q);
			test.itemTypeRoleRelationTests(q);
			test.itemTypeTests(q);
			test.itemUsedTests(q);
			test.roleTests(q);
			test.userTests(q);
			
			
		}
		
				
	}



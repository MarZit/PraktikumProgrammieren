package databaseLager;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import  javax.persistence.NoResultException;

import org.junit.AfterClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

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
//Buchstaben zu Beginn der Methoden dienen nur zur Festlegung der Reihenfolge
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class QueryTest {
	
	Queries q = new Queries();
	
		/**JUnit Tests für alle Item-Queries*/
		@SuppressWarnings("deprecation")
		
		
		@Test
		public void a_ItemTest(){
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
		newItem.setIsOut((byte)0);
		newItem.setLent((byte)0);
		newItem.setItemPicture("picture");
		q.insertItem(newItem);
		System.out.println("ItemInserted");
		
		i = q.getItemByItemID(100);
		assertEquals("Insert: Name muss ItemName sein", "ItemName", i.getName());
		
		itemList = q.getItems();
		assertEquals("GetItems: Anzahl aller Items muss 35 sein", 35, itemList.size());
		
		


		}

		
		// Methode für Delete Item
		@Test(expected = NoResultException.class)
		public void b_ItemDeleteTest(){
		Item i = q.getItemByItemID(100);
		q.deleteItem(i);
		System.out.println("ItemDeleted");
		
		i = q.getItemByItemID(100);
		}
		
		/**JUnit Tests für alle Role-Queries*/
		@Test
		public void c_RoleTests(){
		List <Role> roleList = q.getRoles();
		assertEquals("GetRoles: Anzahl aller Roles muss e sein", 3, roleList.size());

		
		Role r = q.getRoleByRoleID(1);
		assertEquals("GetRoleByRoleId: Name muss admin sein", "admin", r.getRoleName());

		
		r.setRoleName("TestName");
		
		q.updateRole(r);
		System.out.println("RoleUpdated");
		
		r = q.getRoleByRoleID(1);
		assertEquals("Update: Name muss Test sein", "TestName", r.getRoleName());
		
		
		r = new Role();
		r.setRoleId(1500);
		r.setRoleName("Test");
		
		q.insertRole(r);
		
		r = q.getRoleByRoleID(1500);
		assertEquals("Insert: Name muss Test sein", "Test", r.getRoleName());

		
		
		}
		
		
		//Methode für Delete Role
		@Test(expected = NoResultException.class)
		public void d_RoleDeleteTest(){
		Role r = q.getRoleByRoleID(1500);
		q.deleteRole(r);
		
		r = q.getRoleByRoleID(1500);

		}
		
		
		//JUnit Tests für alle User-Queries
		@Test
		public void e_UserTests(){
		List <User> userList = q.getUsers();
		assertEquals("GetUsers: Anzahl aller User muss 6 sein", 6, userList.size());

		
		
		userList = q.getUsersByRoleID(1);
		assertEquals("GetUsersByRoleID: Anzahl muss 5 sein", 5, userList.size());

		User u = q.getUserByNameAndPassword("User", "user");
		assertEquals("GetUserByNameAndPassword: Role muss 3 sein", 3, u.getRole());
		
		u = q.getUserByUserID(1);
		assertEquals("GetUserByUserID: Name muss Admin sein", "Admin", u.getUsername());
		
		

		u.setUsername("TestName");
		
		q.updateUser(u);
		
		u = q.getUserByUserName("TestName");
		assertEquals("Update: Name muss Test sein", "TestName", u.getUsername());
		
		
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

		
		
		}
		
		
		//Methode für Delete User
		@Test(expected = NoResultException.class)
		public void f_UserDeleteTest(){
		
		User u = q.getUserByUserID(1500);
		q.deleteUser(u);
		
		System.out.println("UserDeleted");
		
		u = q.getUserByUserID(1500);

		}
		
		
		/**JUnit Tests für alle ItemType-Queries*/
		@Test
		public void g_ItemTypeTests(){
		
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
		
		
		
		}
		
		
		//Methode für Delete ItemType
		@Test(expected = NoResultException.class)
		public void h_ItemTypeDeleteTest(){
		
		ItemType type = q.getItemTypeByTypeId(1500);
		q.deleteItemType(type);
		
		System.out.println("ItemTypeDeleted");
		
		type = q.getItemTypeByTypeId(1500);

		}
		
		/**JUnit Tests für alle ItemTypeRoleRelation-Queries*/
		@Test
		public void i_ItemTypeRoleRelationTests(){
		
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
		
		
		}
		
		
		//Methode für Delete ItemTypeRoleRelation
		@Test(expected = NoResultException.class)
		public void j_ItemTypeRoleRelationDeleteTest(){
		
		ItemTypeRoleRelation typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
		q.deleteItemTypeRoleRelation(typeRole);
		
		System.out.println("Delete erfolgreich");
		
		typeRole = q.getItemTypeRoleRelationsByRoleAndTypeId(1, 1);
		}

		
		/**JUnit Tests für alle ItemUsed-Queries*/
		@SuppressWarnings("deprecation")
		@Test
		public void k_ItemUsedTests(){
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

		}
		
		
		//Methode für Delete ItemUsed
		@Test(expected = NoResultException.class)
		public void l_ItemUsedDeleteTest(){
		ItemUsed used = q.getItemUsedByItemId(1);
		q.deleteItemUsed(used);
		
		used = q.getItemUsedByItemId(1);

		}
		
		/**JUnit Tests für alle ItemReservation-Queries*/
		@SuppressWarnings("deprecation")
		@Test
		public void m_ItemReservationTests(){
		
 		ItemReservation res = new ItemReservation();
		res.setUserId(1);
		res.setItemId(1);
		res.setStartdate(new Date(117, 11, 13));
		res.setEnddate(new Date(117, 11, 20));
		res.setReservationId(1);
		res.setOpen((byte)1);
		res.setOverrun((byte)1);
		
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
		
		
		}
		
		//Methode für Delete ItemReservation
		@Test(expected = NoResultException.class)
		public void n_ItemReservationDeleteTest(){
		
		ItemReservation res = q.getItemReservationByReservationID(1);
		q.deleteItemReservation(res);
		
		res = q.getItemReservationByReservationID(1);

		}
		
		@AfterClass
		public static void restore(){
			Queries q = new Queries();
			Item i = q.getItemByItemID(1);
			i.setName("VW Golf 7");
			q.updateItem(i);
			
			Role r = q.getRoleByRoleID(1);
			r.setRoleName("admin");
			q.updateRole(r);
			
			User u = q.getUserByUserID(1);
			u.setUsername("Admin");
			q.updateUser(u);
			
			ItemType it = q.getItemTypeByTypeId(1);
			it.setTypeName("PKW");
			q.updateItemType(it);
			
		}
		
		
				
	}


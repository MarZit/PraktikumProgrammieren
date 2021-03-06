package databaseLager;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;


import model.ItemTypeRoleRelationPK_;
import model.Item_;
import model.Role;
import model.Item;
import model.ItemReservation;
import model.ItemReservation_;
import model.ItemType;
import model.ItemTypeRoleRelation;
import model.ItemTypeRoleRelationPK;
import model.ItemUsed_;
import model.ItemType_;
import model.ItemUsed;
import model.ItemTypeRoleRelation_;
import model.Role_;
import model.User;
import model.User_;

/**
 * Klasse enhält alle benötigten Datenbankabfragen welche für das Programm
 * @author Julian
 *
 */
public class Queries {
	
	
	
    private EntityManagerFactory factory;
    
    public Queries (){
    	factory = Persistence.createEntityManagerFactory("database");
    }
	
    
    
    /**Select-Befehle*/
	
    /**Item Select-Befehle*/
	public List<Item> getItemsByItemType(int item_type){
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> item = cq.from(Item.class);
		cq.select(item).where(cb.equal(item.get(Item_.typeId), item_type));
		
		TypedQuery <Item> q = m.createQuery(cq);
		List <Item> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public List<Item> getItemsByItemName(String item_name){
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> item = cq.from(Item.class);
		cq.select(item).where(cb.equal(item.get(Item_.itemName), item_name));
		
		TypedQuery <Item> q = m.createQuery(cq);
		List <Item> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List<Item> getItemsByItemKind(int type_kind) {

		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		
		Root<Item> item = cq.from(Item.class);
		
		
		Subquery<Integer> sq = cq.subquery(Integer.class);
		Root<ItemType> itemType = sq.from(ItemType.class);
		
		sq.select(itemType.get(ItemType_.typeId)).where(cb.equal(itemType.get(ItemType_.typeKind), type_kind));
		cq.select(item).where(cb.in(item.get(Item_.typeId)).value(sq));
		
		TypedQuery <Item> q = m.createQuery(cq);
		List <Item> res = q.getResultList();
		m.close();
		return res;
		
		
		
		
		
	}
	
	public Item getItemByItemID(int item_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> item = cq.from(Item.class);
		cq.select(item).where(cb.equal(item.get(Item_.itemId), item_id));	
		
		TypedQuery <Item> q = m.createQuery(cq);
		Item res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	public List <Item> getItems() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Item> cq = cb.createQuery(Item.class);
		Root<Item> item = cq.from(Item.class);
		cq.orderBy(cb.asc(item.get(Item_.typeId)));
		cq.select(item);	
		
		TypedQuery <Item> q = m.createQuery(cq);
		List <Item> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	
	/**User Select-Befehle*/
	
	public User getUserByNameAndPassword (String name, String pass){
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		
		Predicate username = cb.equal(user.get(User_.username), name);
		Predicate password = cb.equal(user.get(User_.password), pass);
		Predicate p = cb.and(username, password);
		
		cq.select(user).where(p);
		
		TypedQuery <User> q = m.createQuery(cq);
		User res = q.getSingleResult();
		m.close();
		return res;
		
	}
	
	public User getUserByUserID(int user_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user).where(cb.equal(user.get(User_.userId), user_id));	
		
		TypedQuery <User> q = m.createQuery(cq);
		User res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	public User getUserByUserName(String userName) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user).where(cb.equal(user.get(User_.username), userName));	
		
		TypedQuery <User> q = m.createQuery(cq);
		User res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	
	public List <User> getUsersByRoleID(int role_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.orderBy(cb.asc(user.get(User_.lastName)));
		cq.select(user).where(cb.equal(user.get(User_.role), role_id));	
		
		TypedQuery <User> q = m.createQuery(cq);
		List <User> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public List <User> getUsers() {
	
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<User> cq = cb.createQuery(User.class);
		Root<User> user = cq.from(User.class);
		cq.select(user);	
		
		TypedQuery <User> q = m.createQuery(cq);
		List <User> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	
	/**Role Select-Befehle*/
	public List <Role> getRoles() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(Role.class);
		Root<Role> role = cq.from(Role.class);
		cq.orderBy(cb.asc(role.get(Role_.roleName)));
		cq.select(role);	
		
		TypedQuery <Role> q = m.createQuery(cq);
		List <Role> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public Role getRoleByRoleID(int role_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<Role> cq = cb.createQuery(Role.class);
		Root<Role> role = cq.from(Role.class);
		cq.select(role).where(cb.equal(role.get(Role_.roleId), role_id));	
		
		TypedQuery <Role> q = m.createQuery(cq);
		Role res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	
	/**ItemReservation Select-Befehle*/
	
	public ItemReservation getItemReservationByReservationID(int res_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		cq.select(reservation).where(cb.equal(reservation.get(ItemReservation_.reservationId), res_id));	
		
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		ItemReservation res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	public List <ItemReservation> getItemReservationsByItemID(int item_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		cq.select(reservation).where(cb.equal(reservation.get(ItemReservation_.itemId), item_id));	
		
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List <ItemReservation> getItemReservationsByUserID(int user_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		cq.select(reservation).where(cb.equal(reservation.get(ItemReservation_.userId), user_id));	
		
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	

	public List <ItemReservation> getItemReservationsBySingleDate(Date date) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		
		List<Predicate> conditionsList = new ArrayList<Predicate>();
		
		Predicate pg = cb.lessThanOrEqualTo(reservation.get(ItemReservation_.startdate), date); 
		Predicate pl = cb.greaterThanOrEqualTo(reservation.get(ItemReservation_.enddate), date);
		
		conditionsList.add(pg);
		conditionsList.add(pl);
		
		cq.select(reservation).where(conditionsList.toArray(new Predicate[]{}));
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List <ItemReservation> getItemReservationsBetweenDates(Date startdate, Date enddate) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		
		
		Predicate start = cb.between(reservation.get(ItemReservation_.startdate), startdate, enddate);
		Predicate end = cb.between(reservation.get(ItemReservation_.enddate), startdate, enddate);
		
		
		Predicate p = cb.or(start, end);


		cq.select(reservation).where(p);	
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List <ItemReservation> getOpenItemReservations() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		cq.orderBy(cb.asc(reservation.get(ItemReservation_.startdate)));
		cq.select(reservation).where(cb.equal(reservation.get(ItemReservation_.open), 1));	
		
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public List <ItemReservation> getOverrunItemReservations() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> reservation = cq.from(ItemReservation.class);
		cq.orderBy(cb.asc(reservation.get(ItemReservation_.startdate)));
		cq.select(reservation).where(cb.equal(reservation.get(ItemReservation_.overrun), 1));	
		
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List <ItemReservation> getItemReservations() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemReservation> cq = cb.createQuery(ItemReservation.class);
		Root<ItemReservation> resv = cq.from(ItemReservation.class);
		cq.orderBy(cb.asc(resv.get(ItemReservation_.reservationId)));
		cq.select(resv);	
		
		TypedQuery <ItemReservation> q = m.createQuery(cq);
		List <ItemReservation> res = q.getResultList();
		m.close();
		return res;
	    }
	/**ItemType Select-Befehle*/
	
	public ItemType getItemTypeByTypeId(int type_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemType> cq = cb.createQuery(ItemType.class);
		Root<ItemType> reservation = cq.from(ItemType.class);
		cq.select(reservation).where(cb.equal(reservation.get(ItemType_.typeId), type_id));	
		
		TypedQuery <ItemType> q = m.createQuery(cq);
		ItemType res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	public List <ItemType> getItemTypesByTypeKind(int type_kind) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemType> cq = cb.createQuery(ItemType.class);
		Root<ItemType> reservation = cq.from(ItemType.class);
		cq.select(reservation).where(cb.equal(reservation.get(ItemType_.typeKind), type_kind));	
		
		TypedQuery <ItemType> q = m.createQuery(cq);
		List <ItemType> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public List <ItemType> getItemTypes() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemType> cq = cb.createQuery(ItemType.class);
		Root<ItemType> item = cq.from(ItemType.class);
		cq.orderBy(cb.asc(item.get(ItemType_.typeName)));
		cq.select(item);	
		
		TypedQuery <ItemType> q = m.createQuery(cq);
		List <ItemType> res = q.getResultList();
		m.close();
		return res;
	    }
	
	/**ItemTypeRoleRelation Select-Befehle*/
	
	public List <ItemTypeRoleRelation> getItemTypeRoleRelationsByTypeId(int type_id) {
		
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemTypeRoleRelation> cq = cb.createQuery(ItemTypeRoleRelation.class);
		Root<ItemTypeRoleRelation> rel = cq.from(ItemTypeRoleRelation.class);
		Path<ItemTypeRoleRelationPK> pk = rel.get(ItemTypeRoleRelation_.id); 
		cq.select(rel).where(cb.equal(pk.get(ItemTypeRoleRelationPK_.typeId), type_id));	
		
		TypedQuery <ItemTypeRoleRelation> q = m.createQuery(cq);
		List <ItemTypeRoleRelation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public List <ItemTypeRoleRelation> getItemTypeRoleRelationsByRoleId(int role_id) {
		
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemTypeRoleRelation> cq = cb.createQuery(ItemTypeRoleRelation.class);
		Root<ItemTypeRoleRelation> rel = cq.from(ItemTypeRoleRelation.class);
		Path<ItemTypeRoleRelationPK> pk = rel.get(ItemTypeRoleRelation_.id); 
		cq.select(rel).where(cb.equal(pk.get(ItemTypeRoleRelationPK_.roleId), role_id));	
		
		TypedQuery <ItemTypeRoleRelation> q = m.createQuery(cq);
		List <ItemTypeRoleRelation> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public ItemTypeRoleRelation getItemTypeRoleRelationsByRoleAndTypeId(int role_id, int type_id) {
		
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemTypeRoleRelation> cq = cb.createQuery(ItemTypeRoleRelation.class);
		Root<ItemTypeRoleRelation> rel = cq.from(ItemTypeRoleRelation.class);
		Path<ItemTypeRoleRelationPK> pk = rel.get(ItemTypeRoleRelation_.id); 
		Predicate role = cb.equal(pk.get(ItemTypeRoleRelationPK_.roleId), role_id);
		Predicate type = cb.equal(pk.get(ItemTypeRoleRelationPK_.typeId), type_id);
		Predicate p = cb.and(role, type);
		cq.select(rel).where(p);	
		
		TypedQuery <ItemTypeRoleRelation> q = m.createQuery(cq);
		ItemTypeRoleRelation res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	
	/**ItemUsed Select-Befehle*/
	
	public List <ItemUsed> getItemUsedByTypeId(int type_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemUsed> cq = cb.createQuery(ItemUsed.class);
		Root<ItemUsed> used = cq.from(ItemUsed.class);
		cq.select(used).where(cb.equal(used.get(ItemUsed_.typeId), type_id));	
		
		TypedQuery <ItemUsed> q = m.createQuery(cq);
		List <ItemUsed> res = q.getResultList();
		m.close();
		return res;
	    }
	
	public List <ItemUsed> getItemUsedByUserId(int user_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemUsed> cq = cb.createQuery(ItemUsed.class);
		Root<ItemUsed> used = cq.from(ItemUsed.class);
		cq.select(used).where(cb.equal(used.get(ItemUsed_.userId), user_id));	
		
		TypedQuery <ItemUsed> q = m.createQuery(cq);
		List <ItemUsed> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public ItemUsed getItemUsedByItemId(int item_id) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemUsed> cq = cb.createQuery(ItemUsed.class);
		Root<ItemUsed> used = cq.from(ItemUsed.class);
		cq.select(used).where(cb.equal(used.get(ItemUsed_.itemId), item_id));	
		
		TypedQuery <ItemUsed> q = m.createQuery(cq);
		ItemUsed res = q.getSingleResult();
		m.close();
		return res;
	    }
	
	public List <ItemUsed> getItemUsedByDate(Date date) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemUsed> cq = cb.createQuery(ItemUsed.class);
		Root<ItemUsed> used = cq.from(ItemUsed.class);
		cq.select(used).where(cb.equal(used.get(ItemUsed_.date), date));	
		
		TypedQuery <ItemUsed> q = m.createQuery(cq);
		List <ItemUsed> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List <ItemUsed> getItemUsedBetweenDates(Date startdate, Date enddate) {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemUsed> cq = cb.createQuery(ItemUsed.class);
		Root<ItemUsed> used = cq.from(ItemUsed.class);
		
		Predicate p = cb.between(used.get(ItemUsed_.date), startdate, enddate);
		cq.select(used).where(p);	
		
		TypedQuery <ItemUsed> q = m.createQuery(cq);
		List <ItemUsed> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	public List <ItemUsed> getItemsUsed() {
		
		EntityManager m = factory.createEntityManager();
		
		CriteriaBuilder cb = m.getCriteriaBuilder();
		CriteriaQuery<ItemUsed> cq = cb.createQuery(ItemUsed.class);
		Root<ItemUsed> item = cq.from(ItemUsed.class);
		cq.orderBy(cb.asc(item.get(ItemUsed_.date)));
		cq.select(item);	
		
		TypedQuery <ItemUsed> q = m.createQuery(cq);
		List <ItemUsed> res = q.getResultList();
		m.close();
		return res;
	    }
	
	
	
	
	/**Update-Befehle*/
	
	public void updateReservation(ItemReservation reservation) {
	    
    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<ItemReservation> cq = cb.createCriteriaUpdate(ItemReservation.class);
	Root<ItemReservation> res = cq.from(ItemReservation.class);
	cq.set(res.get(ItemReservation_.itemId), reservation.getItemId());
	cq.set(res.get(ItemReservation_.userId), reservation.getUserId());
	cq.set(res.get(ItemReservation_.startdate), reservation.getStartdate());
	cq.set(res.get(ItemReservation_.enddate), reservation.getEnddate());
	cq.set(res.get(ItemReservation_.open), reservation.getOpen());
	cq.set(res.get(ItemReservation_.overrun), reservation.getOverrun());
	cq.set(res.get(ItemReservation_.kilometer), reservation.getKilometer());
	cq.where(cb.equal(res.get(ItemReservation_.reservationId), reservation.getReservationId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	
public void updateItem(Item item) {
	    
    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<Item> cq = cb.createCriteriaUpdate(Item.class);
	Root<Item> res = cq.from(Item.class);
	cq.set(res.get(Item_.itemId), item.getItemId());
	cq.set(res.get(Item_.entrydate), item.getEntrydate());
	cq.set(res.get(Item_.description), item.getDescription());
	cq.set(res.get(Item_.itemPicture), item.getItemPicture());
	cq.set(res.get(Item_.lent), item.getLent());
	cq.set(res.get(Item_.itemName), item.getName());
	cq.set(res.get(Item_.isOut), item.getIsOut());
	cq.set(res.get(Item_.typeId), item.getTypeId());

	cq.where(cb.equal(res.get(Item_.itemId), item.getItemId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}

public void updateUser(User user) {
    
	EntityManager m = factory.createEntityManager();

	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<User> cq = cb.createCriteriaUpdate(User.class);
	Root<User> res = cq.from(User.class);
	cq.set(res.get(User_.userId), user.getUserId());
	cq.set(res.get(User_.role), user.getRole());
	cq.set(res.get(User_.email), user.getEmail());
	cq.set(res.get(User_.firstName), user.getFirstName());
	cq.set(res.get(User_.lastName), user.getLastName());
	cq.set(res.get(User_.newUser), user.getNewUser());
	cq.set(res.get(User_.password), user.getPassword());
	cq.set(res.get(User_.username), user.getUsername());
	cq.where(cb.equal(res.get(User_.userId), user.getUserId()));

	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();

	m.close();

} 


public void updateRole(Role role) {
    
	EntityManager m = factory.createEntityManager();

	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<Role> cq = cb.createCriteriaUpdate(Role.class);
	Root<Role> res = cq.from(Role.class);
	cq.set(res.get(Role_.roleId), role.getRoleId());
	cq.set(res.get(Role_.roleName), role.getRoleName());
	cq.where(cb.equal(res.get(Role_.roleId), role.getRoleId()));

	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();

	m.close();

}

public void updateItemType(ItemType type) {
    
	EntityManager m = factory.createEntityManager();

	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<ItemType> cq = cb.createCriteriaUpdate(ItemType.class);
	Root<ItemType> res = cq.from(ItemType.class);
	cq.set(res.get(ItemType_.typeId), type.getTypeId());
	cq.set(res.get(ItemType_.typeName), type.getTypeName());
	cq.where(cb.equal(res.get(ItemType_.typeId), type.getTypeId()));

	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();

	m.close();

}

public void updateItemTypeRoleRelation(ItemTypeRoleRelation typeRole) {
    
	EntityManager m = factory.createEntityManager();

	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<ItemTypeRoleRelation> cq = cb.createCriteriaUpdate(ItemTypeRoleRelation.class);
	Root<ItemTypeRoleRelation> res = cq.from(ItemTypeRoleRelation.class);
	cq.set(res.get(ItemTypeRoleRelation_.id), typeRole.getId());
	cq.where(cb.equal(res.get(ItemTypeRoleRelation_.id), typeRole.getId()));

	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();

	m.close();

}

public void updateItemUsed(ItemUsed used) {
    
	EntityManager m = factory.createEntityManager();

	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaUpdate<ItemUsed> cq = cb.createCriteriaUpdate(ItemUsed.class);
	Root<ItemUsed> res = cq.from(ItemUsed.class);
	cq.set(res.get(ItemUsed_.itemId), used.getItemId());
	cq.set(res.get(ItemUsed_.date), used.getDate());
	cq.set(res.get(ItemUsed_.typeId), used.getTypeId());
	cq.set(res.get(ItemUsed_.userId), used.getUserId());
	cq.where(cb.equal(res.get(ItemUsed_.itemId), used.getItemId()));

	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();

	m.close();

}

	
	/**Insert-Befehle*/

	public void insertReservation(ItemReservation reservation) {
    	
    	EntityManager m = factory.createEntityManager();
	
	m.getTransaction().begin();
	m.persist(reservation);
	m.getTransaction().commit();
	
	m.close();
    
	}

	public void insertItem(Item item) {
	
	EntityManager m = factory.createEntityManager();

	m.getTransaction().begin();
	m.persist(item);
	m.getTransaction().commit();

	m.close();

	}


	public void insertRole(Role role) {
	
	EntityManager m = factory.createEntityManager();

	m.getTransaction().begin();
	m.persist(role);
	m.getTransaction().commit();

	m.close();

	}


	public void insertUser(User user) {
	
	EntityManager m = factory.createEntityManager();

	m.getTransaction().begin();
	m.persist(user);
	m.getTransaction().commit();

	m.close();

	}
	
	
	public void useItem(ItemUsed used) {
		
		EntityManager m = factory.createEntityManager();

		m.getTransaction().begin();
		m.persist(used);
		m.getTransaction().commit();

		m.close();

	}
	
	
	public void insertItemType(ItemType type) {
		
		EntityManager m = factory.createEntityManager();

		m.getTransaction().begin();
		m.persist(type);
		m.getTransaction().commit();

		m.close();

	}
	
	
	public void insertItemTypeRoleRelation(ItemTypeRoleRelation relation) {
		
		EntityManager m = factory.createEntityManager();

		m.getTransaction().begin();
		m.persist(relation);
		m.getTransaction().commit();

		m.close();

		}


	/**Delete-Befehle*/
	
	public void deleteItem(Item item) {

    EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<Item> cq = cb.createCriteriaDelete(Item.class);
	Root<Item> root = cq.from(Item.class);
	cq.where(cb.equal(root.get(Item_.itemId), item.getItemId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	public void deleteItemReservation(ItemReservation res) {

    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<ItemReservation> cq = cb.createCriteriaDelete(ItemReservation.class);
	Root<ItemReservation> root = cq.from(ItemReservation.class);
	cq.where(cb.equal(root.get(ItemReservation_.reservationId), res.getReservationId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	public void deleteItemType(ItemType type) {

    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<ItemType> cq = cb.createCriteriaDelete(ItemType.class);
	Root<ItemType> root = cq.from(ItemType.class);
	cq.where(cb.equal(root.get(ItemType_.typeId), type.getTypeId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	
	public void deleteItemTypeRoleRelation(ItemTypeRoleRelation rel) {

		
    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<ItemTypeRoleRelation> cq = cb.createCriteriaDelete(ItemTypeRoleRelation.class);
	Root<ItemTypeRoleRelation> root = cq.from(ItemTypeRoleRelation.class);
	cq.where(cb.equal(root.get(ItemTypeRoleRelation_.id), rel.getId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	
	public void deleteItemUsed(ItemUsed used) {

    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<ItemUsed> cq = cb.createCriteriaDelete(ItemUsed.class);
	Root<ItemUsed> root = cq.from(ItemUsed.class);
	cq.where(cb.equal(root.get(ItemUsed_.itemId), used.getItemId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	
	public void deleteRole(Role role) {

    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<Role> cq = cb.createCriteriaDelete(Role.class);
	Root<Role> root = cq.from(Role.class);
	cq.where(cb.equal(root.get(Role_.roleId), role.getRoleId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	
	
	public void deleteUser(User user) {

    	EntityManager m = factory.createEntityManager();
	
	CriteriaBuilder cb = m.getCriteriaBuilder();
	CriteriaDelete<User> cq = cb.createCriteriaDelete(User.class);
	Root<User> root = cq.from(User.class);
	cq.where(cb.equal(root.get(User_.userId), user.getUserId()));
	
	m.getTransaction().begin();
	Query q = m.createQuery(cq);
	q.executeUpdate();
	m.getTransaction().commit();
	
	m.close();
    
	}
	

}

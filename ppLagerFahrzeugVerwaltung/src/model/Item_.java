package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-21T10:08:34.863+0100")
@StaticMetamodel(Item.class)
public class Item_ {
	public static volatile SingularAttribute<Item, Integer> itemId;
	public static volatile SingularAttribute<Item, String> description;
	public static volatile SingularAttribute<Item, Date> entrydate;
	public static volatile SingularAttribute<Item, String> itemPicture;
	public static volatile SingularAttribute<Item, Byte> lent;
	public static volatile SingularAttribute<Item, String> name;
	public static volatile SingularAttribute<Item, Byte> out;
	public static volatile SingularAttribute<Item, Integer> typeId;
}

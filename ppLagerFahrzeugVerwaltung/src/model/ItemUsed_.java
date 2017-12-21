package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-21T10:08:35.130+0100")
@StaticMetamodel(ItemUsed.class)
public class ItemUsed_ {
	public static volatile SingularAttribute<ItemUsed, Integer> itemId;
	public static volatile SingularAttribute<ItemUsed, Date> date;
	public static volatile SingularAttribute<ItemUsed, Integer> typeId;
	public static volatile SingularAttribute<ItemUsed, Integer> userId;
}

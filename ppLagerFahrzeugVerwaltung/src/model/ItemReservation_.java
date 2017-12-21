package model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2017-12-21T10:08:35.089+0100")
@StaticMetamodel(ItemReservation.class)
public class ItemReservation_ {
	public static volatile SingularAttribute<ItemReservation, Integer> reservationId;
	public static volatile SingularAttribute<ItemReservation, Date> enddate;
	public static volatile SingularAttribute<ItemReservation, Integer> itemId;
	public static volatile SingularAttribute<ItemReservation, Integer> kilometer;
	public static volatile SingularAttribute<ItemReservation, Byte> open;
	public static volatile SingularAttribute<ItemReservation, Byte> overrun;
	public static volatile SingularAttribute<ItemReservation, Date> startdate;
	public static volatile SingularAttribute<ItemReservation, Integer> userId;
}

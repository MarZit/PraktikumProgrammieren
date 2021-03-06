package application;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Marcus Zitzelsberger
 *
 */

public enum TypeKindEnum {
	VEHICLE(0), RENTABLE(1), USABLE(2);
	
	int i;
	
	TypeKindEnum(int i){
		this.i = i;
	}
	
	public String toString() {
		switch(i) {
		case 0: return Specifications.getInstance().getResources().getString("vehicle");
		case 1: return Specifications.getInstance().getResources().getString("rentable");
		case 2: return Specifications.getInstance().getResources().getString("usable");
		default: return "";
		}
	}
	
	public int getEnumValue() {
		return i;
	}
	
	public static List<TypeKindEnum> getAll(){
		List<TypeKindEnum> all = new ArrayList<>();
		all.add(VEHICLE);
		all.add(RENTABLE);
		all.add(USABLE);
		return all;
	}
}
